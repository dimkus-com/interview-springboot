package cz.gerasimov.interviewproject.repositories;

import cz.gerasimov.interviewproject.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
}
