package com.example.demonstration_spring_boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserWithDetailDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Date createdAt;
    private UserDetailDTO userDetail;
    private Boolean isActive;

}
