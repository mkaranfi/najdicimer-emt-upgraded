package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.model.Message;
import mk.ukim.finki.wp.model.Role;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Collections;


/**
 * Created by Darko on 2/26/2016.
 */

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/session/{id}", method = RequestMethod.GET)
    public void getUserId(@PathVariable Long id, HttpSession session, HttpServletResponse response) throws IOException {
        session.setAttribute("userId", id);
        response.sendRedirect("http://localhost:8000");
    }

    @RequestMapping("/current")
    public Principal restoreSession(HttpServletRequest request) {
        return null;
        //return userService.getUser((Long) session.getAttribute("userId"));
    }

    //    @RequestMapping(value = "", method = RequestMethod.GET)
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Principal user(Principal user) {
        Logger logger = Logger.getLogger(UserResource.class.getName());
        logger.log(Level.DEBUG, user);
        return user;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        response.sendRedirect("http://localhost:8000");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public List<User> getAllAdmins() {
        return userService.getAllAdmins();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public void removeUser(@PathVariable Long id) {
        userService.remove(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public void updateUser(@PathVariable Long id, @RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String username, @RequestParam String password, @RequestParam String birthDate) {
        User user = userService.getUser(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setBirthDate(birthDate);
        userService.update(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User loginUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userService.logIn(username, password);
        if (user != null)
            session.setAttribute("userId", user.getId());
        return user;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signUpUser(@RequestParam String name, @RequestParam String surname, @RequestParam String birthDate, @RequestParam String email, @RequestParam String username, @RequestParam String password) {
        userService.signUp(name, surname, birthDate, email, username, password);
    }

    @RequestMapping(value = "/message/{messageId}")
    public Message getMessage(@PathVariable Long messageId) {
        return userService.getMessage(messageId);
    }

    @RequestMapping(value = "/message/new", method = RequestMethod.POST)
    public void sendMessage(@RequestParam String content, @RequestParam Long userFromId, @RequestParam Long userToId) {
        userService.sendMessage(content, userToId, userFromId);
    }

    @RequestMapping(value = "/message/received/{userId}", method = RequestMethod.GET)
    public List<Message> getInbox(@PathVariable Long userId) {
        List<Message> messages = userService.getInbox(userId);
        Collections.reverse(messages);
        return messages;
    }

    @RequestMapping(value = "/message/sent/{userId}", method = RequestMethod.GET)
    public List<Message> getOutbox(@PathVariable Long userId) {
        List<Message> messages = userService.getOutbox(userId);
        Collections.reverse(messages);
        return messages;
    }

    @RequestMapping(value = "/message/update/{messageId}", method = RequestMethod.POST)
    public void toggleMessageState(@PathVariable Long messageId, @RequestParam Boolean state) {
        userService.setMessageState(messageId, state);
    }

}
