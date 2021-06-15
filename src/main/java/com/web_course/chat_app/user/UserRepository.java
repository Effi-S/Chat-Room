package com.web_course.chat_app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository
        extends JpaRepository<User, Long>{

    User getById(String id);
    User findUserById(String id);

}
