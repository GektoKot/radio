package com.variant.radio.service;

import com.variant.radio.domain.Comment;
import com.variant.radio.domain.Message;
import com.variant.radio.domain.User;
import com.variant.radio.domain.Views;
import com.variant.radio.dto.EventType;
import com.variant.radio.dto.ObjectType;
import com.variant.radio.repository.CommentRepository;
import com.variant.radio.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepository commentRepository, WsSender wsSender) {
        this.commentRepository = commentRepository;

        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.IdTextComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        Comment commentFromDb = commentRepository.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);
        return commentFromDb;
    }
}
