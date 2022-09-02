package com.hk3fg.board.service;

import com.hk3fg.board.domain.repository.GameRepository;

import com.hk3fg.board.dto.GameDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GameService {

    private GameRepository gameRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public Long saveScore(GameDto gameDto) {
        logger.info("GameService : saveScore / Action : save Data(게임점수) | Activate\n");

        return gameRepository.save(gameDto.toEntity()).getUID_Num();
    }

}
