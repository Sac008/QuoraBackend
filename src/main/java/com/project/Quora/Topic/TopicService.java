package com.project.Quora.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Topic createNewTopic(Topic topic) {
        return this.topicRepository.save(topic);
    }

    public List<Topic> allTopicList() {
        return this.topicRepository.findAll();
    }
}
