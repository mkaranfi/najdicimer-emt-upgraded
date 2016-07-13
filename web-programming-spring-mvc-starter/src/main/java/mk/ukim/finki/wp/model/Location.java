package mk.ukim.finki.wp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Darko on 4/2/2016.
 */

@Entity
@Table(name = "listing_location")
@JsonIgnoreProperties(value = {"listing"})
public class Location extends BaseEntity{
    private String name;
    private String lat;
    private String lng;

    @OneToOne
    private Listing listing;

    public Location() {}

    public Location(String name, String lat, String lng, Listing listing){
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.listing = listing;
    }

    public String getName() {
        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public Listing getListing() {
        return listing;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }
}
