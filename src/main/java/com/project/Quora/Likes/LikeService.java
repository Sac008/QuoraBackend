package com.project.Quora.Likes;

import com.project.Quora.Answer.AnswerRepository;
import com.project.Quora.Comment.CommentRepository;
import com.project.Quora.Question.QuestionRepository;
import com.project.Quora.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LikeService {

    private AnswerRepository answerRepository;
    private CommentRepository commentRepository;
    private QuestionRepository questionRepository;

    private LikeRepository likeRepository;

    private UserRepository userRepository;

    public LikeService(AnswerRepository answerRepository , CommentRepository commentRepository , QuestionRepository questionRepository, UserRepository userRepository , LikeRepository likeRepository) {
        this.answerRepository = answerRepository;
        this.commentRepository = commentRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    public Likes likeThePost(String type , UUID userId , UUID parentId) {

        this.userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Entity with userId: " + userId + " not found"));
        if(type.equalsIgnoreCase("answer")) {
            this.answerRepository.findById(parentId).orElseThrow(() -> new EntityNotFoundException("Answer with UUID: " + parentId + " not found"));
        }
        else if(type.equalsIgnoreCase("comment")) {
            this.commentRepository.findById(parentId).orElseThrow(() -> new EntityNotFoundException("Comment with UUID: " + parentId + " not found"));
        }
        else if(type.equalsIgnoreCase("question")) {
            this.questionRepository.findById(parentId).orElseThrow(() -> new EntityNotFoundException("question with UUID: " + parentId + " not found"));
        }
        else {
            throw new IllegalArgumentException("Please provide valid type");
        }

        Likes likeObj = new Likes();
        likeObj.setParentId(parentId);
        likeObj.setUserId(userId);
        return this.likeRepository.save(likeObj);
    }
}
