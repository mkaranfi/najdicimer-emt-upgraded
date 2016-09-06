package mk.ukim.finki.wp.model;

import mk.ukim.finki.wp.configuration.SerbianNormalizationFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Darko on 2/20/2016.
 */

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Indexed
@AnalyzerDef(name = "analyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SerbianNormalizationFilterFactory.class)
        })
@Table(name = "listings")
public class Listing extends BaseEntity {
    @Field(index = Index.YES, store = Store.NO, analyze = Analyze.YES)
    @Analyzer(definition = "analyzer")
    @Length(max = 70)
    private String title;

    @Field(index = Index.YES, store = Store.NO, analyze = Analyze.YES)
    @Analyzer(definition = "analyzer")
    @Length(max = 5000)
    private String content;

    private String createdOn;

    @Column(name = "image_url")
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(value = FetchMode.SUBSELECT)
    @CollectionTable(name = "listing_image_urls", joinColumns = @JoinColumn(name = "id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "image_url"})})
    @Cascade(value = CascadeType.REMOVE)
    private Set<String> imageURLs;

    @ManyToOne(cascade = javax.persistence.CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    private Location location;

    public Listing() {

    }

    public Listing(String title, String content, String createdOn, ArrayList<String> imageURLs, User user) {
        this.title = title;
        this.content = content;
        this.createdOn = createdOn;
        this.imageURLs = new HashSet<String>(imageURLs);
        this.user = user;
        //locations = new ArrayList<Location>();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public List<String> getImageURLs() {
        return new ArrayList<String>(imageURLs);
    }

    public Location getLocation() {
        return location;
    }

    public User getUser() {
        return user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setImageURLs(List<String> imageURLs) {
        this.imageURLs = new HashSet<String>(imageURLs);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
