package mk.ukim.finki.wp.persistence;

import mk.ukim.finki.wp.model.BaseEntity;
import mk.ukim.finki.wp.model.Listing;
import mk.ukim.finki.wp.model.User;
import mk.ukim.finki.wp.persistence.helper.PredicateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Darko on 12/29/2015.
 */

@Repository
public class BaseRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * SELECT t.* FROM @Table({type}) as t WHERE t.id={id}
     *
     * @param type
     * @param id
     * @param <T>
     * @return
     */
    public <T extends BaseEntity> T getById(Class<T> type, Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        final Root<T> root = cq.from(type);

        Predicate byId = cb.equal(root.get("id"), id);

        cq.where(byId);

        TypedQuery<T> query = em.createQuery(cq);

        return query.getSingleResult();
    }

    public <T extends BaseEntity> T getByUsername(Class<T> type, String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        final Root<T> root = cq.from(type);

        Predicate byUsername = cb.equal(root.get("username"), username);

        cq.where(byUsername);

        TypedQuery<T> query = em.createQuery(cq);

        return query.getSingleResult();
    }

    public <T> List<T> find(Class<T> type, PredicateBuilder<T> predicateBuilder) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        final Root<T> root = cq.from(type);

//    Predicate securityPredicate = getSecurityPredicate(type);

        if (predicateBuilder != null)
            cq.where(predicateBuilder.toPredicate(cb, cq, root));
//    else
//      cq.where(securityPredicate);
        TypedQuery<T> query = em.createQuery(cq);

        return query.getResultList();
    }

    private <T> Predicate getSecurityPredicate(Class<T> type) {
        return null;
    }

    @Transactional
    public <T extends BaseEntity> T saveOrUpdate(T entity) {
        if (entity.getId() != null && !em.contains(entity)) {
            entity = em.merge(entity);
        } else {
            em.persist(entity);
        }
        em.flush();
        return entity;
    }

    @Transactional
    public <T> int delete(Class<T> type, Long id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<T> cd = cb.createCriteriaDelete(type);
        final Root<T> root = cd.from(type);

        CriteriaQuery<T> cq = cb.createQuery(type);
        Predicate byId = cb.equal(root.get("id"), id);
        cd.where(byId);
        int changes = em.createQuery(cd).executeUpdate();
        em.flush();
        return changes;
    }


    //my shit goes here..
    public List<Listing> customListingSearch(String keyword){
        TypedQuery<Listing> q = em.createQuery("SELECT t FROM Listing t WHERE t.title LIKE '%" + keyword + "%' OR t.content LIKE '%" + keyword + "%')", Listing.class);
        return q.getResultList();
    }

    public List<User> customUserSearch(String keyword){
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.name LIKE '%" + keyword + "%' OR u.surname LIKE '%" + keyword + "%')", User.class);
        return q.getResultList();
    }

    public List<Listing> customListingSearchByDate(String date){
        // dateFormat dd/MM/yyyy
        TypedQuery<Listing> q = em.createQuery("SELECT l FROM Listing l WHERE l.createdOn LIKE '" + date + "')", Listing.class);
        return q.getResultList();
    }

    public List<Listing> nearbyListingsSearchByLocation(String currentLat, String currentLng, String maxDistance){
        /*
        SELECT *, ( 3959 * acos( cos( radians(41.9991856) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(21.390435799999977) ) + sin( radians(41.9991856) ) * sin( radians( lat ) ) ) ) * 1.609344 AS distance FROM listing_location HAVING distance < 2 ORDER BY distance
         */
        //this can be optimized with native query, lit a bit of effort but does the job just fine for now! :)
        String straightLineDistance = "( 3959 * acos( cos( radians(" + currentLat + ") ) * cos( radians( l.location.lat ) ) * cos( radians( l.location.lng ) - radians(" + currentLng + ") ) + sin( radians(" + currentLat + ") ) * sin( radians( l.location.lat ) ) ) ) * 1.609344";
        TypedQuery<Listing> q = em.createQuery("SELECT l FROM Listing l WHERE " + maxDistance + " >  " + straightLineDistance + " ORDER BY " + straightLineDistance, Listing.class);

        return q.getResultList();
    }

    public <T> List<T> getPiece(Class<T> type, PredicateBuilder<T> predicateBuilder, int offset, int end) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        final Root<T> root = cq.from(type);

//    Predicate securityPredicate = getSecurityPredicate(type);

        if (predicateBuilder != null)
            cq.where(predicateBuilder.toPredicate(cb, cq, root));
//    else
//      cq.where(securityPredicate);
        TypedQuery<T> query = em.createQuery(cq.orderBy(cb.desc(root.get("id")))).setFirstResult(offset).setMaxResults(end - offset);

        return query.getResultList();
    }
}
