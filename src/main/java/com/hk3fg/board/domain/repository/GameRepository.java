package com.hk3fg.board.domain.repository;

import com.hk3fg.board.domain.entity.BoardEntity;
import com.hk3fg.board.domain.entity.GameEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<GameEntity, Long> {

     Optional<GameEntity> findByuID(String uID);

}
