package com.project.Quora.Comment;

import com.project.Quora.user.BaseModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Comment extends BaseModel {

    private UUID parent_id;

    private String text;

    private UUID user_id;
}
