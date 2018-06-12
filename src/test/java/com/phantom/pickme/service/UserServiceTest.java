package com.phantom.pickme.service;

import com.phantom.pickme.domain.user.*;
import com.phantom.pickme.dto.auth.UserConfirmedResponseDto;
import com.phantom.pickme.dto.auth.UserSignupRequestDto;
import com.phantom.pickme.service.user.UserService;
import com.phantom.pickme.service.userCode.UserCodeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    private static UserCode USER_CODE;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCodeRepository userCodeRepository;

    @Autowired
    private UnConfirmedUserRepository unConfirmedUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {
        USER_CODE = userCodeRepository.save(new UserCode("TestUser", 30333, Sex.MAN));
    }

    @Test
    public void 테스트_회원가입() {
        UserSignupRequestDto dto = UserSignupRequestDto.builder()
                .userCode(USER_CODE.getUserCode())
                .username("TestUsername")
                .password("pw")
                .profileImgSrc("profileImgSrc")
                .phone("010-1111-1111")
                .birthYear(2000)
                .birthMonth(12)
                .birthDay(28)
                .email("asdf1234@gmail.com")
                .postNumber("65535")
                .baseAddr("baseAddr")
                .detailAddr("detailAddr")
                .bio("bio")
                .build();

        userService.signup(dto);

    }

    @Test
    public void 테스트_이메일_인증코드_확인() {
        // given
        UserSignupRequestDto dto = UserSignupRequestDto.builder()
                .userCode(USER_CODE.getUserCode())
                .username("TestUsername")
                .password("pw")
                .profileImgSrc("profileImgSrc")
                .phone("010-1111-1111")
                .birthYear(2000)
                .birthMonth(12)
                .birthDay(28)
                .email("asdf1234@gmail.com")
                .postNumber("65535")
                .baseAddr("baseAddr")
                .detailAddr("detailAddr")
                .bio("bio")
                .build();

        final String CONFIRM_CODE = userService.signup(dto);


        // when
        UserConfirmedResponseDto confirmResult = userService.confirmUser(CONFIRM_CODE);

        // then
        assertNotNull("Saved user should not be null", confirmResult);
        assertEquals(confirmResult.getConfirmedUser().getUsername(), dto.getUsername());
    }

    @After
    public void cleanup() {
        userCodeRepository.deleteAll();
        userRepository.deleteAll();
    }
}
