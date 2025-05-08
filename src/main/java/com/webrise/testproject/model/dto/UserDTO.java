package com.webrise.testproject.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO extends EntityDTO {
    String firstName;
    String middleName;
    String lastName;
}
