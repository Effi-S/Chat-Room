package com.web_course.chat_app.api.message;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Message repository. <br/>
 * This is the repository layer for our message database.
 */
@Repository
public interface MessageRepository
        extends JpaRepository<Message, Long> {

    /** Find all Messages **/
    @Override
    List<Message> findAll();

    /** Save a message to the DB **/
    @Override
    <S extends Message> S save(S s);

    /**
     * Finds all messages with sub string.
     *
     * @param subString the sub string to search.
     * @return list of messages containing this sub-string
     */
    @Query("SELECT m FROM Message m WHERE m.message like %?1%")
    List<Message> findAllMessagesWithSubString(String subString);

    /**
     * Finds messages from a given username.
     *
     * @param username the username to search.
     * @return list of messages sent from this user.
     */
    @Query("SELECT m FROM Message m WHERE m.username = ?1")
    List<Message> findMessagesFromUser(String username);

    /**
     * <pre>
     * Use this to find the last i messages from the DB.
     *
     *  Usage:
     *      PageRequest.ofSize(i). PageRequest.ofSize(5)
     *</pre>
     *
     * @param pageable the pageable of size i.
     * @return the last i messages.
     */
    @Query(value="SELECT m FROM Message m ORDER BY m.mid DESC")
    List<Message> findLastI(Pageable pageable);
}
