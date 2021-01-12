package com.variant.radio.repository;

import com.variant.radio.domain.Message;
import com.variant.radio.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  MessageRepository extends JpaRepository<Message, Integer> {
    @EntityGraph(attributePaths = {"comments"})
    Page<Message> findByAuthorIn(List<User> users, Pageable pageable);
}
