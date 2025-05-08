package com.webrise.testproject.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionDTO implements Serializable {
    private String error;
    private String details;
    private int httpStatus;
    private LocalDateTime timeStamp;
}
