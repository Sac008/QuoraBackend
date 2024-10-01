package com.project.Quora.Likes;

import com.project.Quora.user.HexToUUIdConvertor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LikeController {
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping(value = "/{type}/{parentId}/likes" , produces = "application/json")
    public ResponseEntity<?> likeThePost(@PathVariable String type , @PathVariable String parentId , @RequestBody LikeDTO user) {
        if(!isValidHexUUID(parentId)) {
            throw new IllegalArgumentException("Please provide valid UUID format");
        }

        UUID parent_id = HexToUUIdConvertor.hexToUUID(parentId);
        if(this.likeService.likeThePost(type , user.getUserId() , parent_id) instanceof Likes) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessageDTO("Liked successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO("Something went wrong!"));
    }

    public boolean isValidHexUUID(String hexUUID) {
        String regex = "^0x[0-9a-fA-F]{32}$";
        return hexUUID.matches(regex);
    }
}
