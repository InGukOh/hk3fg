package com.hk3fg.board.service;

import com.hk3fg.board.domain.entity.GameEntity;
import com.hk3fg.board.domain.repository.GameRepository;

import com.hk3fg.board.dto.CommentDto;
import com.hk3fg.board.dto.GameDto;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class GameService {

    private GameRepository gameRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public Long saveScore(GameDto gameDto) {
        logger.info("GameService : saveScore / Action : save Data(게임점수) | Activate\n");

        return gameRepository.save(gameDto.toEntity()).getPlayer_Num();
    }

    public List<GameDto> getScorelist() {
        logger.info("GameService : getScorelist / Action : getting Entity | start");

        List<GameEntity> gameEntities = gameRepository.findAll(Sort.by(Sort.Direction.DESC, "gameScore"));
        List<GameDto> gameDtoList = new ArrayList<>();

        for (GameEntity gameEntity : gameEntities) {
            gameDtoList.add(this.convertEntityToDto(gameEntity));
        }
        logger.info("GameService : getScorelist / Action : getting Entity | end\n");
        return gameDtoList;
    }

    private GameDto convertEntityToDto(GameEntity gameEntity) {
        return GameDto.builder()
                .uID(gameEntity.getUID())
                .game_score(gameEntity.getGameScore())
                .player_Num(gameEntity.getPlayer_Num())
                .build();
    }
}
