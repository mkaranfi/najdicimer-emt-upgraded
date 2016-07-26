package mk.ukim.finki.wp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Darko on 2/19/2016.
 */

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"sentMessages", "receivedMessages"})
public class User extends BaseEntity {
    @Length(max = 15)
    private String name;

    @Length(max = 25)
    private String surname;

    @Length(max = 10)
    //format: dd/MM/yyyy
    private String birthDate;

    @Email
    @Length(max = 50)
    private String email;

    @Column(name = "username", unique = true)
    @Length(max = 50)
    private String username;

    @NotEmpty
    @Length(max = 60)
    private String password;

    private String imageURL;

    @OneToMany(mappedBy = "userTo", fetch = FetchType.EAGER)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "userFrom", fetch = FetchType.EAGER)
    private List<Message> receivedMessages;

    private String uploadPath;

    private Boolean isAdmin;

    public User() {
    }

    public User(String name, String surname, String birthDate, String email, String username, String password, String imageURL, Boolean isAdmin, String uploadPath) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.username = username;
        this.password = password;
        this.imageURL = imageURL;
        this.isAdmin = isAdmin;
        this.uploadPath = uploadPath;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Boolean getIsAdmin() {

        return isAdmin;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
