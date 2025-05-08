package com.webrise.testproject.controller;

import com.webrise.testproject.model.dto.UserSubscriptionDTO;
import com.webrise.testproject.service.UserSubscriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/user/{userId}/subscription")
@RequiredArgsConstructor
@Tag(name = "UserSubscriptionController", description = "Работа с подписками пользователя")
public class UserSubscriptionController {

    private final UserSubscriptionService userSubscriptionService;

    @GetMapping
    ResponseEntity<Set<UserSubscriptionDTO>> getUserSubscriptions(@PathVariable Long userId) {
        return ResponseEntity.ok(userSubscriptionService.getAllUserSubscriptions(userId));
    }

    @PostMapping("/{subscriptionId}")
    ResponseEntity<Void> addUserSubscription(@PathVariable Long userId,
                                             @PathVariable Long subscriptionId) {
        userSubscriptionService.addUserSubscription(userId, subscriptionId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{subscriptionId}")
    ResponseEntity<Void> deleteUserSubscription(@PathVariable Long userId,
                                                @PathVariable Long subscriptionId) {
        userSubscriptionService.deleteUserSubscription(userId, subscriptionId);
        return ResponseEntity.ok().build();
    }
}
