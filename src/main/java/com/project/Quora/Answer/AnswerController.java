package com.project.Quora.Answer;

import com.project.Quora.user.HexToUUIdConvertor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/quora")
public class AnswerController {

    private AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/questions/{questionId}/answers")
    public ResponseEntity<?> postAnswer(@RequestBody AnswerDto newAnswer , @PathVariable(required = true) String questionId) {
        if(!isValidHexUUID(questionId)) {
            throw new IllegalArgumentException("Please provide valid UUID format");
        }
        UUID question_id = HexToUUIdConvertor.hexToUUID(questionId);
        Answer answer = this.answerService.saveAnswerToDB(newAnswer, question_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @PutMapping("/answers/{answerId}")
    public ResponseEntity<?> updateAnswer(@RequestBody AnswerUpdateDTO updatedText , @PathVariable String answerId) {
        if(!isValidHexUUID(answerId)) {
            throw new IllegalArgumentException("Please provide valid answer id");
        }

        UUID answer_id = HexToUUIdConvertor.hexToUUID(answerId);
        Answer answer = this.answerService.updateAnswerContent(updatedText , answer_id);
        return ResponseEntity.status(HttpStatus.OK).body(answer);
    }

    public boolean isValidHexUUID(String hexUUID) {
        String regex = "^0x[0-9a-fA-F]{32}$";
        return hexUUID.matches(regex);
    }
}
