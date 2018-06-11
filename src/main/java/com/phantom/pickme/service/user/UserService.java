package com.phantom.pickme.service.user;

import com.phantom.pickme.domain.user.User;
import com.phantom.pickme.domain.user.UserCode;
import com.phantom.pickme.domain.user.UserRepository;
import com.phantom.pickme.dto.UserSignupDto;
import com.phantom.pickme.service.userCode.UserCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCodeService userCodeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User signup(UserSignupDto dto) {
        UserCode userCode = userCodeService.getUserCodeInfoFromUserCodeStr(dto.getUserCode());
        User user = User.builder()
                .name(userCode.getName())
                .postNumber(dto.getPostNumber())
                .username(dto.getUsername())
                .baseAddr(dto.getBaseAddr())
                .bio(dto.getBio())
                .birthDay(dto.getBirthDay())
                .birthMonth(dto.getBirthMonth())
                .birthYear(dto.getBirthYear())
                .detailAddr(dto.getDetailAddr())
                .email(dto.getEmail())
                .profileImgSrc(dto.getProfileImgSrc())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phone(dto.getPhone())
                .sex(userCode.getSex())
                .stuNum(userCode.getStuNum())
                .build();

        userCodeService.dropUserCode(userCode.getUserCode());

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("No user found with username '%s'", username)));
    }
}
