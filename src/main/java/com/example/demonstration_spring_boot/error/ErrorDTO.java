package com.example.demonstration_spring_boot.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorDTO {

    private String message;
    private List<FieldErrorDTO> errors;

}
