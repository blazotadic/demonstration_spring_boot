package com.example.demonstration_spring_boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDetailDTO {

    private Integer id;
    private String address;
    private Integer age;
    private String phoneNumber;

}
