package com.web_course.chat_app.api.message;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository
        extends JpaRepository<Message, Long> {

    @Override
    List<Message> findAll();

    @Override
    <S extends Message> S save(S s);

    @Query("SELECT m FROM Message m WHERE m.message like %?1%")
    List<Message> findAllMessagesWithSubString(String subString);

    @Query("SELECT m FROM Message m WHERE m.username = ?1")
    List<Message> findMessagesFromUser(String username);

    @Query(value="SELECT m FROM Message m ORDER BY m.mid DESC")
    List<Message> findLast5(Pageable pageable);
}
