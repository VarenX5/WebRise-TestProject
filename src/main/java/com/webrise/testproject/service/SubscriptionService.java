package com.webrise.testproject.service;

import com.webrise.testproject.mapper.SubscriptionMapper;
import com.webrise.testproject.model.dto.SubscriptionDTO;
import com.webrise.testproject.model.entity.Subscription;
import com.webrise.testproject.repository.SubscriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Transactional(readOnly = true)
    public List<SubscriptionDTO> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptionMapper.toDtoList(subscriptions);
    }

    @Transactional(readOnly = true)
    public SubscriptionDTO getSubscriptionInfo(Long id) {
        Subscription subscription = getSubscriptionById(id);
        return subscriptionMapper.toDto(subscription);
    }

    @Transactional
    public SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionMapper.toEntity(subscriptionDTO);
        return subscriptionMapper.toDto(subscriptionRepository.save(subscription));

    }

    @Transactional
    public void updateSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = getSubscriptionById(subscriptionDTO.getId());
        subscription.setName(subscriptionDTO.getName());
        subscriptionRepository.save(subscription);
    }

    @Transactional
    public void deleteSubscriptionById(Long id) {
        Subscription subscription = getSubscriptionById(id);
        subscriptionRepository.delete(subscription);
    }

    @Transactional(readOnly = true)
    public SubscriptionDTO getMostPopularSubscription() {
        Subscription subscription = subscriptionRepository.getMostPopularSubscription().orElseThrow(
                () -> new EntityNotFoundException("Could not find any active subscriptions on any users."));
        return subscriptionMapper.toDto(subscription);
    }

    //Главное не забыть, что когда такой метод вызывается из того же класса, аннотация не будет работать, так как она
    //работает через прокси. Но в нашем случае это не важно, так как все методы, которые его вызывают, выполняются в транзакции.
    @Transactional
    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Subscription with id " + id + " was not found."));
    }
}
