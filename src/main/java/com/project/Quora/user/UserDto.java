package com.project.Quora.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    String email;

    String username;

    public UserDto(String email , String username) {
        this.username = username;
        this.email = email;
    }
}
