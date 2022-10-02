package com.hk3fg.board.dto;

import com.hk3fg.board.domain.entity.GameEntity;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GameDto {

    private  Long player_Num;

    private String uID;

    private int game_score;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GameEntity toEntity(){

        logger.info("GameDto : toEntity / Action : saving Data(게임 점수) | start");

        logger.info("uID_Num : " + player_Num + " uID : " + uID + " gameScore : " + game_score);

        GameEntity gameEntity = GameEntity.builder()
                .player_Num (player_Num)
                .uID(uID)
                .game_score(game_score)
                .build();

        logger.info("GameDto : toEntity / Action : saving Data(게임 점수) | end\n");

        return gameEntity;
    }
    @Builder
    public GameDto(String uID, Long player_Num, int game_score) {
        logger.info("GameDto : GameDto / Action : loading gameScore : "+ uID+" |");
        this.uID = uID;
        this.player_Num = player_Num;
        this.game_score = game_score;

    }

}
