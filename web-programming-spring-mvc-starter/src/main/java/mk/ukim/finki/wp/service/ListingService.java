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
public interface ListingService {
    Listing createListing(String title, String content, Date createdOn, ArrayList<MultipartFile> images, User user, String locationName, String lat, String lng);

    void updateListing(Listing listing);

    void delete(Long id);

    Listing getById(Long id);

    List<Listing> getAll();

    List<Listing> getAllListingsByUser(Long userId);

    User getUser(Long userId);

    List<Listing> search(String keyword);

    List<Listing> filterByDate(Integer day, Integer month, Integer year);

    List<Report> getAllReports();

    List<Report> getAllUnreadReports();

    void saveReport(String content, User userFrom, Listing listing);

    void updateReport(Long id, Boolean seen);

    Report getReportById(Long id);

    List<Listing> nearbyListingsSearchByLocation(String currentLat, String currentLng, String maxDistance);

    List<Listing> getPiece(int offset, int end);
}
