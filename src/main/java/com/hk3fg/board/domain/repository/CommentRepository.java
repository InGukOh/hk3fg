package com.hk3fg.board.domain.repository;

import com.hk3fg.board.domain.entity.CommentEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository  extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByContentNum(Long contentNum);

    /*Optional<CommentEntity> findByuID(String uID);*/
}
