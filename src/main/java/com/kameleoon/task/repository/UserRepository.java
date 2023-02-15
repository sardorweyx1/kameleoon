package com.kameleoon.task.repository;

import com.kameleoon.task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByMail(String mail);

    boolean existsById(Long id);
}
