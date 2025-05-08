package com.webrise.testproject.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class EntityDTO implements Serializable {
    Long id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Long version;
}
