package com.web_course.chat_app.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository
        extends JpaRepository<User, Long>{

    @Override
    List<User> findAll();

    @Override
    <S extends User> S save(S s);

    User findUserBySession(String session);


}
