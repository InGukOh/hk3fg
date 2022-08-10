package com.hk3fg.board.dto;

import com.hk3fg.board.domain.entity.UserEntity;
import lombok.*;

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

    public UserEntity userEntity(){
        UserEntity userEntity = UserEntity.builder()
                .uID_Num(uID_Num)
                .uID(uID)
                .uPW(uPW)
                .build();
        return userEntity;
    }
    @Builder
    public UserDto(Long uID_Num, String uID, String uPW){
        this.uID_Num = uID_Num;
        this.uID = uID;
        this.uPW = uPW;
    }
}
