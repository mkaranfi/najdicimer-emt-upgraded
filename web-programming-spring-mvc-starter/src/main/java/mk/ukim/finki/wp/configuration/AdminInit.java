package mk.ukim.finki.wp.configuration;

/**
 * Created by Mile on 29/08/2016.
 */

import mk.ukim.finki.wp.model.Role;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by ristes on 3/9/16.
 */
@Component
public class AdminInit {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        List<User> adminList = userRepository.findByUsername("admin");
        if (adminList.isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ROLE_ADMIN);
            userRepository.save(admin);
        }
    }
}
