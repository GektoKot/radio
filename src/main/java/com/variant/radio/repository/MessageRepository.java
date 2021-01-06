package com.variant.radio.repository;

import com.variant.radio.domain.Message;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface  MessageRepository extends JpaRepository<Message, Integer> {

    @EntityGraph(attributePaths = {"comments"})
    List<Message> findAll();
}
