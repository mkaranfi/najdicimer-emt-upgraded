package mk.ukim.finki.wp.service;

import mk.ukim.finki.wp.model.Listing;
import mk.ukim.finki.wp.model.Report;
import mk.ukim.finki.wp.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Darko on 2/21/2016.
 */
public interface IListingService {
    public Listing createListing(String title, String content, Date createdOn, ArrayList<MultipartFile> images, User user, String locationName, String lat, String lng);
    public void updateListing(Listing listing);
    public void delete(Long id);
    public Listing getById(Long id);
    public List<Listing> getAll();
    public List<Listing> getAllListingsByUser(Long userId);
    public User getUser(Long userId);
    public List<Listing> search(String keyword);
    public List<Listing> filterByDate(Integer day, Integer month, Integer year);
    public List<Report> getAllReports();
    public List<Report> getAllUnreadReports();
    public void saveReport(String content, User userFrom, Listing listing);
    public void updateReport(Long id, Boolean seen);
    public Report getReportById(Long id);
    public List<Listing> nearbyListingsSearchByLocation(String currentLat, String currentLng, String maxDistance);
    public List<Listing> getPiece(int offset, int end);
}
