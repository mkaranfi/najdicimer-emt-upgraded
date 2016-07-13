package mk.ukim.finki.wp.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Darko on 3/18/2016.
 */

@Entity
@Table(name = "reports")
public class Report extends BaseEntity {
    @Length(max = 5000)
    private String content;

    @Length(max = 10)
    //format: dd/MM/yyyy
    private String submittedOn;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    private User userFrom;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "listing_id", nullable = false)
    private Listing listing;

    private Boolean seen;

    public Report() {
    }

    public Report(String content, String submittedOn, User userFrom, Listing listing) {
        this.content = content;
        this.submittedOn = submittedOn;
        this.userFrom = userFrom;
        this.listing = listing;
        seen = false;
    }

    public String getContent() {
        return content;
    }

    public String getSubmittedOn() {
        return submittedOn;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public Listing getListing(){
        return listing;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSubmittedOn(String submittedOn) {
        this.submittedOn = submittedOn;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public void setListing(Listing listing){
        this.listing = listing;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }
}

