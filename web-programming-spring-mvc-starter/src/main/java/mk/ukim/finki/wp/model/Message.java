package mk.ukim.finki.wp.model;

import org.hibernate.annotations.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Created by Darko on 2/20/2016.
 */

@Entity
@Table(name = "messages")
public class Message extends BaseEntity {
    @Length(max = 5000)
    private String content;

    @Length(max = 10)
    //format: dd/MM/yyyy
    private String sentOn;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_from", nullable = false)
    private User userFrom;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_to", nullable = false)
    private User userTo;

    private Boolean seen;

    public Message() {
    }

    public Message(String content, String sentOn, User userFrom, User userTo) {
        this.content = content;
        this.sentOn = sentOn;
        this.userFrom = userFrom;
        this.userTo = userTo;
        seen = false;
    }

    public String getContent() {
        return content;
    }

    public String getSentOn() {
        return sentOn;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSentOn(String sentOn) {
        this.sentOn = sentOn;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }
}
