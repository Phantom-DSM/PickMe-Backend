package com.phantom.pickme.service;

import com.phantom.pickme.domain.user.*;
import com.phantom.pickme.dto.UserSignupDto;
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

    private static String USER_CODE;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCodeService userCodeService;

    @Autowired
    private UserCodeRepository userCodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {
        USER_CODE = userCodeRepository.save(new UserCode("TestUser", 30333, Sex.MAN)).getUserCode();
    }

    @Test
    public void 테스트_회원가입() {
        UserCode userCodeObj = userCodeService.getUserCodeInfoFromUserCodeStr(USER_CODE);
        UserSignupDto dto = UserSignupDto.builder()
                .userCode(userCodeObj.getUserCode())
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

        User user = userService.signup(dto);
        assertNotNull("Saved user should not be null", user);
        assertEquals(user.getUsername(), dto.getUsername());
    }

    @After
    public void cleanup() {
        userCodeRepository.deleteAll();
        userRepository.deleteAll();
    }
}
