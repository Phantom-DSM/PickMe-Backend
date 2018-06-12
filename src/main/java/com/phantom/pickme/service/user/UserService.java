package com.phantom.pickme.service.user;

import com.phantom.pickme.domain.user.*;
import com.phantom.pickme.dto.auth.UserConfirmedResponseDto;
import com.phantom.pickme.dto.auth.UserSignupRequestDto;
import com.phantom.pickme.exception.AlreadyExistsException;
import com.phantom.pickme.service.unConfirmedUser.UnConfirmedUserService;
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
    @Autowired
    private UnConfirmedUserService unConfirmedUserService;

    @Transactional
    public UserConfirmedResponseDto confirmUser(String confirmCode) {
        UnConfirmedUser unConfirmedUser = unConfirmedUserService.findUnConfirmedUserByConfirmCode(confirmCode);
        UserCode userCode = unConfirmedUser.getUserCode();

        User user = User.builder()
                .name(userCode.getName())
                .postNumber(unConfirmedUser.getPostNumber())
                .username(unConfirmedUser.getUsername())
                .baseAddr(unConfirmedUser.getBaseAddr())
                .bio(unConfirmedUser.getBio())
                .birthDay(unConfirmedUser.getBirthDay())
                .birthMonth(unConfirmedUser.getBirthMonth())
                .birthYear(unConfirmedUser.getBirthYear())
                .detailAddr(unConfirmedUser.getDetailAddr())
                .email(unConfirmedUser.getEmail())
                .profileImgSrc(unConfirmedUser.getProfileImgSrc())
                .password(passwordEncoder.encode(unConfirmedUser.getPassword()))
                .phone(unConfirmedUser.getPhone())
                .sex(userCode.getSex())
                .stuNum(userCode.getStuNum())
                .build();

        userCodeService.dropUserCode(userCode.getUserCode());
        unConfirmedUserService.dropUnConfirmedUser(unConfirmedUser.getUserId());

        return new UserConfirmedResponseDto(userRepository.save(user), unConfirmedUser.getPassword());
    }

    @Transactional
    public String signup(UserSignupRequestDto dto) {
        UserCode userCode = userCodeService.getUserCodeInfoFromUserCodeStr(dto.getUserCode());

        if (userRepository.existsUserByUsername(dto.getUsername()))
            throw new AlreadyExistsException(String.format("username '%s' already exists", dto.getUsername()));

        if (userRepository.existsUserByEmail(dto.getEmail()))
            throw new AlreadyExistsException(String.format("email '%s' already exists", dto.getEmail()));

        if (userRepository.existsUserByPhone(dto.getPhone()))
            throw new AlreadyExistsException(String.format("phone '%s' already exists", dto.getPhone()));

        if (userRepository.existsUserByProfileImgSrcAndProfileImgSrcIsNotNull(dto.getProfileImgSrc()))
            throw new AlreadyExistsException(String.format("profile image src '%s' already exists", dto.getProfileImgSrc()));

        final UnConfirmedUser user = UnConfirmedUser.builder()
                .userCode(userCode)
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
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .build();

        unConfirmedUserService.save(user);

        // TODO: confirm mail send

        return user.getUserId();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("No user found with username '%s'", username)));
    }
}
