package mk.ukim.finki.wp.persistence;

import mk.ukim.finki.wp.model.Message;
import mk.ukim.finki.wp.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darko on 2/20/2016.
 */
public interface IUserRepository {
    public void save(User user);
    public User logIn(String username, String password);
    public User findById(Long id);
    public List<User> findAll();
    public void delete(Long id);
    public List<User> getAllAdmins();
    public void sendMessage(Message message);
    public List<Message> getInbox(Long userId);
    public List<Message> getOutbox(Long userId);
    public Message getMessage(Long messageId);
    public void saveMessage(Message message);
}
