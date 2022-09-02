package com.hk3fg.board.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "user_game_info")
public class GameEntity {
    @Column(nullable = false)
    private Long player_Num ;

    @Id
    @Column(length = 10, nullable = false)
    private String uID;

    @Column(length = 10)
    private int gameScore;

    @Builder
    public GameEntity(Long player_Num , String uID, int gameScore) {
        this.player_Num  = player_Num ;
        this.uID = uID;
        this.gameScore = gameScore;
    }
}
