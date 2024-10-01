package com.project.Quora.user;

import com.project.Quora.Question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class User extends BaseModel{

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    List<Question> questions = new ArrayList<>();
}
