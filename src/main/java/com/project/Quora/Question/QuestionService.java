package com.project.Quora.Question;

import com.project.Quora.user.User;
import com.project.Quora.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    private UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository , UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public Question postNewQuestion(QuestionRequestDTO newQuestion , User user) {
            Question q = new Question();
            q.setBody(newQuestion.getBody());
            q.setTitle(newQuestion.getTitle());
            q.setTopics(newQuestion.getTopics());
            q.setUserId(user);
            Question ans = this.questionRepository.save(q);
            return ans;
    }

    public Optional<User> userExistOrNot(UUID id) {
        Optional<User> user = this.userRepository.findById(id);
        return user;
    }

    public List<Question> questionMatchedWithText(String text) {
        return this.questionRepository.findAllQuestionsMatchedWithText(text);
    }
    public List<Question> questionMatchedWithTag(String tag) {
        return this.questionRepository.findAllQuestionsMatchedWithTag(tag);
    }

}
