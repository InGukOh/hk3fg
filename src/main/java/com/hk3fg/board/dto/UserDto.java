package com.hk3fg.board.dto;

import com.hk3fg.board.domain.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {

    private Long uID_Num;

    private String uID;

    private String uPW;

    private LocalDateTime join_date;

    private LocalDateTime cancel_date;

    public UserEntity userEntity(){
        System.out.println("UserDTO : 실행됨");
        UserEntity userEntity = UserEntity.builder()
                .uID_Num(uID_Num)
                .uID(uID)
                .uPW(uPW)
                //.join_date(join_date)
                .build();
        return userEntity;
    }
}
