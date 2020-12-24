package com.variant.radio.repository;

import com.variant.radio.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;



public interface  MessageRepository extends JpaRepository<Message, Integer> {
}
