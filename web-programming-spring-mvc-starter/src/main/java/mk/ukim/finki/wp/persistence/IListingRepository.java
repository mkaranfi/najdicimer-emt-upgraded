package mk.ukim.finki.wp.persistence;

import mk.ukim.finki.wp.model.Listing;
import mk.ukim.finki.wp.model.Location;
import mk.ukim.finki.wp.model.Report;
import mk.ukim.finki.wp.model.User;

import java.util.List;

/**
 * Created by Darko on 2/21/2016.
 */
public interface IListingRepository {
    public Listing findById(Long id);
    public List<Listing> findAll();
    public Listing saveOrUpdate(Listing listing);
    public void delete(Long id);
    public User findUser(Long userId);
    public List<Listing> getAllListingsByUser(Long userId);
    public List<Listing> search(String keyword);
    public List<Listing> filterByDate(String date);
    public List<Report> getAllReports();
    public List<Report> getAllUnreadReports();
    public void saveOrUpdateReport(Report report);
    public Report getReportById(Long id);
    public Location saveOrUpdateLocation(Location location);
    public List<Listing> nearbyListingsSearchByLocation(String currentLat, String currentLng, String maxDistance);
    public List<Listing> getPiece(int offset, int end);
}
