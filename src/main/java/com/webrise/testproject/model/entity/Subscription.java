package com.webrise.testproject.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriptions")
public class Subscription extends BaseEntity {

    private String name;

    //Более корректно было бы сделать 3 сущности с такими отношениями: user 1-N userSubscription N-1 subscription.
    //В первую очередь, так как того требует нормализация БД
    //Во вторую очередь, чтобы указать там такие поля как: дата начала подписки, дата окончания.
    @ManyToMany(mappedBy = "subscriptions")
    private Set<User> users;
}
