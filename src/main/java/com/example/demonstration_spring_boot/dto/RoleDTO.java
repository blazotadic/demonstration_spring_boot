package com.example.demonstration_spring_boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleDTO {

    private Integer id;
    private String name;
    private String description;

}
