package com.webrise.testproject.service;

import com.webrise.testproject.mapper.SubscriptionMapper;
import com.webrise.testproject.model.dto.UserSubscriptionDTO;
import com.webrise.testproject.model.entity.Subscription;
import com.webrise.testproject.model.entity.User;
import com.webrise.testproject.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserSubscriptionService {
    private final UserRepository userRepository;
    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @Transactional(readOnly = true)
    public Set<UserSubscriptionDTO> getAllUserSubscriptions(Long userId) {
        User user = userRepository.findWithSubscriptionsById(userId).orElseThrow(
                () -> new EntityNotFoundException("User with id " + userId + " was not found."));
        return subscriptionMapper.toUserSubscriptionDtoSet(user.getSubscriptions());
    }

    @Transactional
    public void deleteUserSubscription(Long userId, Long subscriptionId) {
        User user = userRepository.findWithSubscriptionsById(userId).orElseThrow(
                () -> new EntityNotFoundException("User with id " + userId + " was not found."));
        Set<Subscription> subscriptions = user.getSubscriptions();
        if (subscriptions.stream().anyMatch(s -> s.getId().equals(subscriptionId))) {
            user.setSubscriptions(subscriptions.stream().filter(s -> !s.getId().equals(subscriptionId)).collect(Collectors.toSet()));
            userRepository.save(user);
        }
    }

    @Transactional
    public void addUserSubscription(Long userId, Long subscriptionId) {
        User user = userRepository.findWithSubscriptionsById(userId).orElseThrow(
                () -> new EntityNotFoundException("User with id " + userId + " was not found."));
        Set<Subscription> subscriptions = user.getSubscriptions();
        if (subscriptions.stream().noneMatch(s -> s.getId().equals(subscriptionId))) {
            Subscription subscription = subscriptionService.getSubscriptionById(subscriptionId);
            subscriptions.add(subscription);
            user.setSubscriptions(subscriptions);
            userRepository.save(user);
        }
    }
}
