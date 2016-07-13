package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.Message;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by Darko on 2/20/2016.
 */
public interface IUserService {
    void signUp(String name, String surname, String birthDate, String email, String username, String password, Boolean isAdmin, MultipartFile image);

    void signUp(String name, String surname, String birthDate, String email, String username, String password);

    User logIn(String username, String password);

    void remove(Long id);

    void update(User user);

    User getUser(Long id);

    List<User> getAllUsers();

    List<User> getAllAdmins();

    void sendMessage(String content, Long userFrom, Long userTo);

    List<Message> getInbox(Long userId);

    List<Message> getOutbox(Long userId);

    void setMessageState(Long messageId, Boolean state);

    Message getMessage(Long messageId);
}
