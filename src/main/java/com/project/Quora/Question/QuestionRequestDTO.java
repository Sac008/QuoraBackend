package com.project.Quora.Question;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.Quora.user.HexToUUIdConvertor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Getter
@Setter
public class QuestionRequestDTO {

    @JsonDeserialize(using = HexToUUIdConvertor.class)
    private UUID userId;

    private String title;

    private String body;

    private String[] topics;
}
