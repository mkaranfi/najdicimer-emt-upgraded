package mk.ukim.finki.wp.persistence.impl;

import mk.ukim.finki.wp.model.Listing;
import mk.ukim.finki.wp.model.Location;
import mk.ukim.finki.wp.model.Report;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.BaseRepository;
import mk.ukim.finki.wp.persistence.IListingRepository;
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
 * Created by Darko on 2/21/2016.
 */
@Repository
public class ListingRepository implements IListingRepository {

    @Autowired
    BaseRepository baseRepository;

    @Override
    public Listing findById(Long id) {
        return baseRepository.getById(Listing.class, id);
    }

    @Override
    public List<Listing> findAll() {
        return baseRepository.find(Listing.class, null);
    }

    @Override
    public Listing saveOrUpdate(Listing listing) {
        return baseRepository.saveOrUpdate(listing);
    }

    @Override
    public void delete(Long id) {
        baseRepository.delete(Listing.class, id);
    }

    @Override
    public User findUser(Long userId) {
       return baseRepository.getById(User.class, userId);
    }

    public List<Listing> getAllListingsByUser(final Long userId)
    {
        return baseRepository.find(Listing.class, new PredicateBuilder<Listing>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<Listing> cq, Root<Listing> root) {
                return cb.equal(root.get("user"), userId);
            }
        });
    }

    @Override
    public List<Listing> search(String keyword) {
        return baseRepository.customListingSearch(keyword);
    }

    @Override
    public List<Listing> filterByDate(String date) {
        return baseRepository.customListingSearchByDate(date);
    }

    @Override
    public List<Report> getAllReports() {
        return baseRepository.find(Report.class, null);
    }

    @Override
    public List<Report> getAllUnreadReports() {
        return baseRepository.find(Report.class, new PredicateBuilder<Report>() {
            @Override
            public Predicate toPredicate(CriteriaBuilder cb, CriteriaQuery<Report> cq, Root<Report> root) {
                return cb.equal(root.get("seen"), false);
            }
        });
    }

    @Override
    public void saveOrUpdateReport(Report report) {
        baseRepository.saveOrUpdate(report);
    }

    @Override
    public Report getReportById(Long id) {
        return baseRepository.getById(Report.class, id);
    }

    @Override
    public Location saveOrUpdateLocation(Location location) {
        return baseRepository.saveOrUpdate(location);
    }

    @Override
    public List<Listing> nearbyListingsSearchByLocation(String currentLat, String currentLng, String maxDistance) {
        return baseRepository.nearbyListingsSearchByLocation(currentLat, currentLng, maxDistance);
    }

    @Override
    public List<Listing> getPiece(int offset, int end) {
        return baseRepository.getPiece(Listing.class, null, offset, end);
    }
}