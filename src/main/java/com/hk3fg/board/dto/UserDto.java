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
        //System.out.println("UDto : 들어감1");
        UserEntity userEntity = UserEntity.builder()
                .uID_Num(uID_Num)
                .uID(uID)
                .uPW(uPW)
                .join_date(join_date)
                .build();
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
