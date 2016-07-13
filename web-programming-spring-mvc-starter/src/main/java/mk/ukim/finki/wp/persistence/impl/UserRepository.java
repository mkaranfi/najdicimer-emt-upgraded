package mk.ukim.finki.wp.persistence.impl;

import mk.ukim.finki.wp.model.Message;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.BaseRepository;
import mk.ukim.finki.wp.persistence.IUserRepository;
import mk.ukim.finki.wp.persistence.helper.PredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Darko on 2/20/2016.
 */

@Repository
public class UserRepository implements IUserRepository {
    @Autowired
    BaseRepository baseRepository;

    @Override
    public void save(User user) {
        baseRepository.saveOrUpdate(user);
    }

    @Override
    public User logIn(String username, String password) {
        List<User> users = findByUsername(username);

        if (users.size() != 0){ //the user exists
            if (users.get(0).getPassword().equals(password))
                return users.get(0);
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        return baseRepository.getById(User.class, id);
    }

    private List<User> findByUsername(final String username){
        return baseRepository.find(User.class, new PredicateBuilder<User>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<User> cq, Root<User> root) {
                return cb.equal(root.get("username"), username);
            }
        });
    }

    @Override
    public List<User> findAll() {
        return baseRepository.find(User.class, null);
    }

    @Override
    public void delete(Long id) {
        baseRepository.delete(User.class, id);
    }

    public List<User> getAllAdmins()
    {
        return baseRepository.find(User.class, new PredicateBuilder<User>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<User> cq, Root<User> root) {
                return cb.equal(root.get("isAdmin"), true);
            }
        });
    }

    @Override
    public void sendMessage(Message message) {
        baseRepository.saveOrUpdate(message);
    }

    @Override
    public List<Message> getInbox(final Long userId) {
        return baseRepository.find(Message.class, new PredicateBuilder<Message>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<Message> cq, Root<Message> root) {
                return cb.equal(root.get("userFrom"), userId);
            }
        });
    }

    @Override
    public List<Message> getOutbox(final Long userId) {
        return baseRepository.find(Message.class, new PredicateBuilder<Message>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<Message> cq, Root<Message> root) {
                return cb.equal(root.get("userTo"), userId);
            }
        });
    }

    @Override
    public Message getMessage(final Long messageId) {
        //THIS METHOD IS NOT SAFE, IF THE MESSAGE DOESN'T EXISTS IT WOULD THROW NULL POINTER EXCEPTION!!
        return baseRepository.find(Message.class, new PredicateBuilder<Message>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<Message> cq, Root<Message> root) {
                return cb.equal(root.get("id"), messageId);
            }
        }).get(0);
    }

    @Override
    public void saveMessage(Message message) {
        baseRepository.saveOrUpdate(message);
    }

}
