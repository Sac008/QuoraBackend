package com.project.Quora.Topic;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.project.Quora.user.BaseModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Topic extends BaseModel {

    private String name;
}
