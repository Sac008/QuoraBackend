package com.project.Quora.Question;

import com.project.Quora.user.HexToUUIdConvertor;
import com.project.Quora.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quora/questions")
public class QuestionController{

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<?> postQuestion(@RequestBody QuestionRequestDTO newQuestion) {
//        newQuestion.setUserId(HexToUUIdConvertor.hexToUUID(newQuestion.getUserId().toString()));

        Optional<User> user = this.questionService.userExistOrNot(newQuestion.getUserId());
        if(user.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.questionService.postNewQuestion(newQuestion , user.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @GetMapping("/search")
    public ResponseEntity<?> matchedQuestions(@RequestParam(required = false) String text , @RequestParam(required = false) String tag) {
        if(text == null && tag == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such question found");
        List<Question> matchedWithText = new ArrayList<>();
        List<Question> matchedWithTag = new ArrayList<>();
        if(text != null) {
            matchedWithText = this.questionService.questionMatchedWithText(text);
        }
        if(tag != null) {
            matchedWithTag = this.questionService.questionMatchedWithTag(tag);
        }
        matchedWithTag.addAll(matchedWithText);
        return ResponseEntity.status(HttpStatus.OK).body(matchedWithTag);
    }
}

























// Create a method in service that checks if a user exist or not and call that in controller and if it exists





















