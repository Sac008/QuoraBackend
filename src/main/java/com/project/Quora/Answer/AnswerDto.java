package com.project.Quora.Answer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.Quora.user.HexToUUIdConvertor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AnswerDto {

    @JsonDeserialize(using = HexToUUIdConvertor.class)
    private UUID user_id;

    private String text;
}
