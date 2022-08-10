package com.hk3fg.board.service;



import com.hk3fg.board.domain.entity.UserEntity;
import com.hk3fg.board.domain.repository.UserRepository;
import com.hk3fg.board.dto.UserDto;

import lombok.AllArgsConstructor;
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

    //회원가입
    @Transactional
    public Long signUp(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setUPW(passwordEncoder.encode(userDto.getUPW()));

        return userRepository.save(userDto.userEntity()).getUID_Num();
    }
    @Override
    public UserDetails loadUserByUsername(String uID) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityWrapper = userRepository.findByUID(uID);
        UserEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if("admin".equals(uID)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        return new User(userEntity.getUID(), userEntity.getUPW(), authorities);
    }


}
