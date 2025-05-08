package com.webrise.testproject.controller;

import com.webrise.testproject.model.dto.SubscriptionDTO;
import com.webrise.testproject.service.SubscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
@Tag(name = "SubscriptionController", description = "Работа с подписками")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping()
    ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        return ResponseEntity.ok(subscriptionService.createSubscription(subscriptionDTO));
    }

    @GetMapping("/{subscriptionId}")
    ResponseEntity<SubscriptionDTO> getSubscriptionInfo(@PathVariable Long subscriptionId) {
        return ResponseEntity.ok(subscriptionService.getSubscriptionInfo(subscriptionId));
    }

    @PutMapping()
    ResponseEntity<Void> updateSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        subscriptionService.updateSubscription(subscriptionDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{subscriptionId}")
    ResponseEntity<Void> deleteSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.deleteSubscriptionById(subscriptionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    ResponseEntity<List<SubscriptionDTO>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @GetMapping("/top")
    ResponseEntity<SubscriptionDTO> getMostPopularSubscription(){
        return ResponseEntity.ok(subscriptionService.getMostPopularSubscription());
    }
}
