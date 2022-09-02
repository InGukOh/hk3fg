package com.hk3fg.board.dto;

import com.hk3fg.board.domain.entity.UserEntity;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long uID_Num;

    private String uID;

    private String uPW;

    private LocalDateTime join_date;

    private LocalDateTime cancel_date;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public UserEntity userEntity(){
        logger.info("UserDto : toEntity / Action : saving Data(유저 가입정보) | start");
        UserEntity userEntity = UserEntity.builder()
                .uID_Num(uID_Num)
                .uID(uID)
                .uPW(uPW)
                .join_date(join_date)
                .build();

        logger.info("UserDto : toEntity / Action : saving Data(유저 가입정보) | end\n");

        return userEntity;
    }
    @Builder
    public UserDto(Long uID_Num, String uID, String uPW, LocalDateTime join_date){
        //System.out.println("UDto : 들어감2");
        this.uID_Num = uID_Num;
        this.uID = uID;
        this.uPW = uPW;
        this.join_date = join_date;
    }
}
