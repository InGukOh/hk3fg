package com.hk3fg.board.domain.repository;

import com.hk3fg.board.domain.entity.CommentEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository  extends JpaRepository<CommentEntity, Long> {

    /*Optional<CommentEntity> findByUID(String uID);*/
}
