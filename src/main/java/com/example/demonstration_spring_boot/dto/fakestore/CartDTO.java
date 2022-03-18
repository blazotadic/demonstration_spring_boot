package com.example.demonstration_spring_boot.dto.fakestore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartDTO {

    private Integer id;
    private Integer userId;
    private String date;
    private List<ProductFromCartDTO> products = new ArrayList<>();

    @JsonProperty("__v")
    private Integer v;


}
