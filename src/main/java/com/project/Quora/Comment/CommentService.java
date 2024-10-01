package com.project.Quora.Comment;

import com.project.Quora.Answer.AnswerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private AnswerRepository answerRepository;

    public CommentService(CommentRepository commentRepository , AnswerRepository answerRepository) {
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
    }

    public Comment commentOnAnswer(CommentDTO comment , UUID answer_id) {
        this.answerRepository.findById(answer_id).orElseThrow(() -> new EntityNotFoundException("Answer with id: " + answer_id + " not found"));
        Comment obj = new Comment();
        obj.setText(comment.getText());
        obj.setUser_id(comment.getUser_id());
        obj.setParent_id(answer_id);
        return this.commentRepository.save(obj);
    }

    public Comment commentOnComment(CommentDTO comment , UUID comment_id) {
        this.commentRepository.findById(comment_id).orElseThrow(() -> new EntityNotFoundException("Comment with id: " + comment_id + " not found"));
        Comment obj = new Comment();
        obj.setText(comment.getText());
        obj.setUser_id(comment.getUser_id());
        obj.setParent_id(comment_id);
        return this.commentRepository.save(obj);
    }
}
