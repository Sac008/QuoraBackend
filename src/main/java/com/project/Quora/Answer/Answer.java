package com.project.Quora.Answer;

import com.project.Quora.Question.Question;
import com.project.Quora.user.BaseModel;
import com.project.Quora.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer extends BaseModel {

    @ManyToOne
    private Question question_id;

    @Column(nullable = false)
    private String text;

    @OneToOne
    private User user_id;

}
