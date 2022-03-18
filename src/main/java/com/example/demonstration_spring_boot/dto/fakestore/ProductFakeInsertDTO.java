package com.example.demonstration_spring_boot.dto.fakestore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFakeInsertDTO {

    private Integer id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;


}
