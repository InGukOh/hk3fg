package com.hk3fg.board.domain.repository;

import com.hk3fg.board.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public static void test(){
        System.out.println("UserRep : 실행됨");
    }
}
