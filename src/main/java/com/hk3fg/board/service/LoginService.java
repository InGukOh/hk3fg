package com.hk3fg.board.service;

import com.hk3fg.board.domain.repository.UserRepository;
import com.hk3fg.board.dto.UserDto;

import javax.transaction.Transactional;

public class LoginService {

    private UserRepository userRepository;

    @Transactional
    public Long Login(UserDto userDto) {
        return null;
    }

}
