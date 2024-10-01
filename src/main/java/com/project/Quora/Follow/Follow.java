package com.project.Quora.Follow;

import com.project.Quora.user.BaseModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Follow extends BaseModel {

    private UUID userId;

    private UUID targetUserId;
}
