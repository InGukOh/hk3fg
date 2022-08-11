package com.hk3fg.board.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity

@Table(name = "user_info")
public class UserEntity  {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long uID_Num;

    @Column(length = 10, nullable = false)
    private String uID;

    @Column(length = 100, nullable = false)
    private String uPW;

    @Column(nullable = false)
    private LocalDateTime join_date;

    @LastModifiedDate
    private LocalDateTime cancel_date;


    @Builder
    public UserEntity(Long uID_Num,
                      String uID,
                      String uPW,
                      LocalDateTime join_date,
                      LocalDateTime cancel_date) {
        System.out.println("UserEnt : 실행됨");
        System.out.println("jd : " + join_date);
        this.uID_Num = uID_Num;
        this.uID = uID;
        this.uPW = uPW;
        this.join_date = LocalDateTime.now();
        this.cancel_date = cancel_date;
    }
}