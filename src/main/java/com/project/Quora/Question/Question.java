package com.project.Quora.Question;

import com.project.Quora.user.BaseModel;
import com.project.Quora.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Question extends BaseModel {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @ElementCollection // specifies that this is collection of basic types(String , Integer , Double etc) Just like one-to-many relationship here question has many topics but it is not an entity here just basic type so used @ElementCollection.
    @CollectionTable(name = "question_topics" , joinColumns = @JoinColumn(name = "question_id")) // specifies the name of join table where topics will be stored and @JoinColum attribute specifies name of foreign key that refers to primary key of question entity.
    @Column(name = "topics") // name of colum in question_topics table where topics will be stored.
    private String[] topics;

    @ManyToOne
    private User userId;
}
