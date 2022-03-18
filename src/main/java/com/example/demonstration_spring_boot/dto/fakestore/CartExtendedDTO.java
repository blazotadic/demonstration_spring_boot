package com.example.demonstration_spring_boot.dto.fakestore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartExtendedDTO extends CartDTO {

    private String status;
    private String message;


}
