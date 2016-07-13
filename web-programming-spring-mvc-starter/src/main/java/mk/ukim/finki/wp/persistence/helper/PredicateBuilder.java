package mk.ukim.finki.wp.persistence.helper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Darko on 12/29/2015.
 */

public interface PredicateBuilder<T> {
    Predicate toPredicate(CriteriaBuilder cb,
                          CriteriaQuery<T> cq,
                          Root<T> root);
}