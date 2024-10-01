package com.project.Quora.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question , UUID> {

    @Query(value = "SELECT * FROM question WHERE body LIKE CONCAT('%', :text, '%')", nativeQuery = true)
    List<Question > findAllQuestionsMatchedWithText(String text);

    @Query(value = "SELECT q.* from Question q JOIN question_topics qt ON q.id = qt.question_id WHERE qt.topics LIKE %:tag%" , nativeQuery = true)
    List<Question> findAllQuestionsMatchedWithTag(String tag);

}
