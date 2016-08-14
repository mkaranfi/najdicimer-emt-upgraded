package mk.ukim.finki.wp.authentication;


import mk.ukim.finki.wp.model.Provider;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Mile on 27/07/2016.
 */

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Provider provider;

    public LoginSuccessHandler(Provider provider) {
        this.provider = provider;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        List<User> users = userRepository.findByUsername(authentication.getName());
        User user = null;
        if (!users.isEmpty()) {
            user = users.get(0);
        }
        if (user == null) {
            user = new User();
            String fullName = authentication.getName();
            String[] names = fullName.split(" ");
            user.setName(names[0]);
            user.setSurname(names[1]);
            user.setUsername(fullName);
            userRepository.save(user);
        }
        httpServletRequest.getSession(false).setAttribute("userId", user.getId());
        httpServletResponse.sendRedirect("http://localhost:8080/api/user/session/" + user.getId());
//        setDefaultTargetUrl("http://localhost:8080/api/user/session/" + user.getId());
//        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
    }
}