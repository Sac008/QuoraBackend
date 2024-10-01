package com.project.Quora.Follow;

import com.project.Quora.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.text.FieldPosition;
import java.util.UUID;

@Service
public class FollowService {

    private FollowRepository followRepository;
    private UserRepository userRepository;

    public FollowService(FollowRepository followRepository , UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public Follow saveTheFollower(UUID user_id , UUID targetUserId) {
        this.userRepository.findById(user_id).orElseThrow(() -> new EntityNotFoundException("user with id: " + user_id + " not found"));
        this.userRepository.findById(targetUserId).orElseThrow(() -> new EntityNotFoundException("user with id: " + targetUserId + " not found"));
        Follow follow = new Follow();
        follow.setUserId(user_id);
        follow.setTargetUserId(targetUserId);
        return this.followRepository.save(follow);
    }
}
