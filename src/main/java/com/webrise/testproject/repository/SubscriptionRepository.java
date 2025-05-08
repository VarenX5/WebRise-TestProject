package com.webrise.testproject.repository;

import com.webrise.testproject.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query(value = "select s.* from user_subscriptions us " +
            "join subscriptions s on (us.subscription_id = s.id) " +
            "group by s.id " +
            "order by COUNT(s.id) DESC " +
            "limit 1", nativeQuery = true)
    Optional<Subscription> getMostPopularSubscription();
}
