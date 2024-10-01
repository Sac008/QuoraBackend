package com.project.Quora.Comment;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.Quora.user.HexToUUIdConvertor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CommentDTO {

    @JsonDeserialize(using = HexToUUIdConvertor.class)
    private UUID user_id;

    private String text;
}
