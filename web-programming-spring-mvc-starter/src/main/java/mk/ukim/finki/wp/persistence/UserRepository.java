package mk.ukim.finki.wp.persistence;

import mk.ukim.finki.wp.model.Message;
import mk.ukim.finki.wp.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darko on 2/20/2016.
 */
public interface UserRepository {
    void save(User user);

    User logIn(String username, String password);

    List<User> findAll();

    User findById(Long id);

    List<User> findByUsername(String username);

    void delete(Long id);

    List<User> getAllAdmins();

    void sendMessage(Message message);

    List<Message> getInbox(Long userId);

    List<Message> getOutbox(Long userId);

    Message getMessage(Long messageId);

    void saveMessage(Message message);
}
