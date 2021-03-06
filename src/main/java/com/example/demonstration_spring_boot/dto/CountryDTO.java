package com.example.demonstration_spring_boot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CountryDTO implements Serializable {

    private Integer id;
    private String code;
    private String name;


}
