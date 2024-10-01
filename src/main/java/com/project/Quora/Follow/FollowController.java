package com.project.Quora.Follow;

import com.project.Quora.Likes.MessageDTO;
import com.project.Quora.user.HexToUUIdConvertor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class FollowController {

    private FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }
    @PostMapping("/{userId}/follow/{targetUserId}")
    public ResponseEntity<?> followAnotherUser(@PathVariable String userId , @PathVariable String targetUserId) {
        if(!isValidHexUUID(userId) && !isValidHexUUID(targetUserId)) {
            throw new IllegalArgumentException("Please provide valid userId");
        }

        UUID user_id = HexToUUIdConvertor.hexToUUID(userId);
        UUID targetUser = HexToUUIdConvertor.hexToUUID(targetUserId);
        if(this.followService.saveTheFollower(user_id , targetUser) instanceof Follow) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO("Follow/follower successfully saved"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO("Something went wrong"));
    }
    public boolean isValidHexUUID(String hexUUID) {
        String regex = "^0x[0-9a-fA-F]{32}$";
        return hexUUID.matches(regex);
    }

}
