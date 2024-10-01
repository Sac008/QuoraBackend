package com.project.Quora.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody Topic topic) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.topicService.createNewTopic(topic));
    }

    @GetMapping
    public ResponseEntity<List<Topic>> allTopics() {
        return ResponseEntity.status(HttpStatus.OK).body(this.topicService.allTopicList());
    }
}
