package mk.ukim.finki.wp.configuration;

import mk.ukim.finki.wp.authentication.LoginSuccessHandler;
import mk.ukim.finki.wp.authentication.OAuth2TokenService;
import mk.ukim.finki.wp.authentication.OAuthClientResource;
import mk.ukim.finki.wp.model.Provider;
import mk.ukim.finki.wp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.filter.CompositeFilter;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mile on 24/07/2016.
 */

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
//                .and()
//                .withUser("admin").password("admin123").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf() should be enabled
        http
                .csrf().disable();

        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/admin/**")
                .hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .logout()
                .invalidateHttpSession(true);

        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll();

        http.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(oauth2AuthenticationFilter(), SessionManagementFilter.class);
    }

    private Filter oauth2AuthenticationFilter() throws Exception {
        OAuth2AuthenticationProcessingFilter filter = new OAuth2AuthenticationProcessingFilter();
        filter.setStateless(false);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    private Filter ssoFilter() {

        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<Filter>();

        filters.add(
                getOauthFilter(
                        "/login/facebook",
                        facebook(),
                        facebookSuccessHandler()
                )
        );

        filter.setFilters(filters);
        return filter;

    }

    @Bean
    @ConfigurationProperties("facebook")
    OAuthClientResource facebook() {
        return new OAuthClientResource();
    }

    @Bean
    LoginSuccessHandler facebookSuccessHandler() {
        return new LoginSuccessHandler(Provider.FACEBOOK);
    }

    public Filter getOauthFilter(
            String loginUrl,
            OAuthClientResource client,
            AuthenticationSuccessHandler successHandler) {
        OAuth2ClientAuthenticationProcessingFilter oauthFilter =
                new OAuth2ClientAuthenticationProcessingFilter(loginUrl);
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        oauthFilter.setRestTemplate(template);
        oauthFilter.setTokenServices(
                new OAuth2TokenService(
                        client.getResource().getUserInfoUri(),
                        client.getClient().getClientId()
                )
        );
        oauthFilter.setAuthenticationSuccessHandler(successHandler);
        return oauthFilter;
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }


    @Bean
    public FilterRegistrationBean requestContextFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestContextFilter());
        registration.setOrder(-105);
        return registration;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}