package com.web_course.chat_app.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long>{

    @Override
    List<User> findAll();

    @Override
    <S extends User> S save(S s);

    @Query("SELECT u from User u WHERE u.username = ?1")
    Optional<User> findUser(String username);

}
