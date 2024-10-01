package com.project.Quora.Answer;

import com.project.Quora.Question.Question;
import com.project.Quora.Question.QuestionRepository;
import com.project.Quora.user.User;
import com.project.Quora.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.boot.model.process.internal.UserTypeResolution;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;
    private UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository , QuestionRepository questionRepository , UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public Answer saveAnswerToDB(AnswerDto answer , UUID questionID) {
        Question question = this.questionRepository.findById(questionID).orElseThrow(() -> new EntityNotFoundException("Question not found with ID: "+ questionID));;
        User user = this.userRepository.findById(answer.getUser_id()).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + answer.getUser_id()));
        Answer ans = new Answer();
        ans.setQuestion_id(question);
        ans.setText(answer.getText());
        ans.setUser_id(user);
        return this.answerRepository.save(ans);
    }

    public Answer updateAnswerContent(AnswerUpdateDTO updatedAnsContent , UUID answerId) {
        Answer ans = this.answerRepository.findById(answerId).orElseThrow(() -> new EntityNotFoundException("Answer with Id: " + answerId + " not found"));
        ans.setText(updatedAnsContent.getText());
        return this.answerRepository.save(ans);
    }
}
