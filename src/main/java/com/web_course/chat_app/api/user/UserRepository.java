package com.web_course.chat_app.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface User repository. <br/>
 * This is the repository layer of the user table in the DB.
 */
@Repository
public interface UserRepository
        extends JpaRepository<User, Long>{

    /** Find all of the users in the DB **/
    @Override
    List<User> findAll();

    /** Save a User to the DB **/
    @Override
    <S extends User> S save(S s);

    /**
     * Find user a particular user.
     *
     * @param username the username of the user to find.
     * @return the optional containing the user.
     */
    @Query("SELECT u from User u WHERE u.username = ?1")
    Optional<User> findUser(String username);

}
