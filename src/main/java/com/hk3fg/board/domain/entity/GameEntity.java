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
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long uID_Num;

    @Column(length = 10, nullable = false)
    private String uID;

    @Column(length = 10)
    private int gameScore;

    @Builder
    public GameEntity(Long uID_Num, String uID, int gameScore) {
        this.uID_Num = uID_Num;
        this.uID = uID;
        this.gameScore = gameScore;
    }
}
