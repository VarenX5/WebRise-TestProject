package com.webrise.testproject.repository;

import com.webrise.testproject.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "subscriptions", type = EntityGraph.EntityGraphType.FETCH)
    Optional<User> findWithSubscriptionsById(Long id);
}
