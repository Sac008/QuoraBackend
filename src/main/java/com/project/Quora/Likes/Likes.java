package com.project.Quora.Likes;

import com.project.Quora.user.BaseModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Likes extends BaseModel {

    private UUID parentId;

    private UUID userId;
}
