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

    private int gameScore;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public GameEntity toEntity(){

        logger.info("GameDto : toEntity / Action : saving Data(게임 점수) | start");

        logger.info("uID_Num : " + player_Num + " uID : " + uID + " gameScore : " + gameScore);

        GameEntity gameEntity = GameEntity.builder()
                .player_Num (player_Num )
                .uID(uID)
                .gameScore(gameScore)
                .build();

        logger.info("GameDto : toEntity / Action : saving Data(게임 점수) | end\n");

        return gameEntity;
    }
  /*  @Builder
    public BoardDto(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        logger.info("BoardDto : BoardDto / Action : loading id : "+ id+" |");
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }*/

}
