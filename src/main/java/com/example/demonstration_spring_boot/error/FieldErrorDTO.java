package com.example.demonstration_spring_boot.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FieldErrorDTO {

    private final String field;
    private final String translationKey;
    private final String defaultMessage;

}
