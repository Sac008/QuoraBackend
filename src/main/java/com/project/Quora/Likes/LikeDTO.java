package com.project.Quora.Likes;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.Quora.user.HexToUUIdConvertor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LikeDTO {

    @JsonDeserialize(using = HexToUUIdConvertor.class)
    private UUID userId;
}
