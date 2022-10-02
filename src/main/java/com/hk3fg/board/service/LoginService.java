package com.hk3fg.board.service;

import com.hk3fg.board.dto.UserDto;
import com.hk3fg.board.domain.entity.UserEntity;
import com.hk3fg.board.domain.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService implements UserDetailsService {

    private UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //회원가입
    @Transactional
    public Long signUp(UserDto userDto) {
        logger.info("LoginService : signUp / Action : Add UserDATA to DB | start");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setUPW(passwordEncoder.encode(userDto.getUPW()));

        logger.info("LoginService : signUp / Action : Add UserDATA to DB | end\n");
        return userRepository.save(userDto.userEntity()).getUID_Num();
    }
    @Override
    public UserDetails loadUserByUsername(String uID) throws UsernameNotFoundException {
        logger.info("LoginService : loadUserByUsername / Action : get UserDATA from DB | start");

        Optional<UserEntity> userEntityWrapper = userRepository.findByuID(uID);
        UserEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if("admin".equals(uID)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        logger.info("LoginService : loadUserByUsername / Action : get UserDATA from DB | end\n");
        return new User(userEntity.getUID(), userEntity.getUPW(), authorities);
    }
    @Transactional
    public UserDto getUserInfo(String uID){
        if(uID.equals("anonymousUser")){
            return null;
        }
        Optional<UserEntity> userEntityWrapper = userRepository.findByuID(uID);
        UserEntity userEntity = userEntityWrapper.get();

        logger.info("userEnt : " +userEntity);

        return this.convertEntityToDto(userEntity);
    }

    private UserDto convertEntityToDto(UserEntity userEntity) {

        return UserDto.builder()
                .uID_Num(userEntity.getUID_Num())
                .uID(userEntity.getUID())
                .build();
    }


}
