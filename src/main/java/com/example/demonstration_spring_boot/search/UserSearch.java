package com.example.demonstration_spring_boot.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSearch {

    private String userIdGreater;
    private String userIdLess;
    private String usernameContain;
    private String roleDescriptionContain;
}
