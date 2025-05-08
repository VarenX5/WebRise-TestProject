package com.webrise.testproject.mapper;

import com.webrise.testproject.model.dto.SubscriptionDTO;
import com.webrise.testproject.model.dto.UserSubscriptionDTO;
import com.webrise.testproject.model.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionDTO toDto(Subscription subscription);

    List<SubscriptionDTO> toDtoList(List<Subscription> user);

    @Mapping(target = "subscriptionId", source = "id")
    UserSubscriptionDTO toUserSubscriptionDto(Subscription subscription);

    Set<UserSubscriptionDTO> toUserSubscriptionDtoSet(Set<Subscription> subscriptions);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "users", ignore = true)
    Subscription toEntity(SubscriptionDTO subscriptionDTO);
}
