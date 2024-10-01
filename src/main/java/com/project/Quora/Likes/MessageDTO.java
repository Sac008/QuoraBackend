package com.project.Quora.Likes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
public class MessageDTO implements Serializable {

    private String message;

    public MessageDTO(String message) {
        this.message = message;
    }
}
