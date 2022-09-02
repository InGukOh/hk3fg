package com.hk3fg.board.dto;

import com.hk3fg.board.domain.entity.GameEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GameDto {

    private  Long id;

    private String uID;

    private int gameScore;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GameEntity toEntity(){

        logger.info("GameDto : toEntity / Action : saving Data(게임 점수) | start");

        GameEntity gameEntity = GameEntity.builder()
                .uID_Num(id)
                .uID(uID)
                .gameScore(gameScore)
                .build();

        logger.info(String.valueOf(gameEntity));
        logger.info("GameDto : toEntity / Action : saving Data(게임 점수) | end\n");

        return gameEntity;
    }
}
