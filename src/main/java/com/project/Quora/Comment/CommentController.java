package com.project.Quora.Comment;

import com.project.Quora.user.HexToUUIdConvertor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/answers/{answer_id}/comments")
    public ResponseEntity<?> commentOnAnswer(@RequestBody CommentDTO comment , @PathVariable String answer_id) {
        if(!isValidHexUUID(answer_id)) {
            throw new IllegalArgumentException("Please provide valid UUID format");
        }

        UUID answerId = HexToUUIdConvertor.hexToUUID(answer_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.commentService.commentOnAnswer(comment , answerId));
    }

    @PostMapping("/comments/{comment_id}/comments")
    public ResponseEntity<?> commentOnComment(@RequestBody CommentDTO comment , @PathVariable String comment_id) {
        if(!isValidHexUUID(comment_id)) {
            throw new IllegalArgumentException("Please provide valid UUID format");
        }

        UUID commentId = HexToUUIdConvertor.hexToUUID(comment_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.commentService.commentOnComment(comment , commentId));
    }

    public boolean isValidHexUUID(String hexUUID) {
        String regex = "^0x[0-9a-fA-F]{32}$";
        return hexUUID.matches(regex);
    }
}
