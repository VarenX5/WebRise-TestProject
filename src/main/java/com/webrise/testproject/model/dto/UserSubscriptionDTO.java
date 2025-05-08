package com.webrise.testproject.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Используем обрезанную версию DTO, так как version, createdAt, updatedAt - это поля,
 * которые относятся к сущности подписки, а не подписки пользователя и в итоге может возникнуть недопонимание.
 */
@Getter
@Setter
public class UserSubscriptionDTO {
    Long subscriptionId;
    String name;
}
