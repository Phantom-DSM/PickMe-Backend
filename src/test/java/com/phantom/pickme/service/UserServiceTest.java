package com.phantom.pickme.service;

import com.phantom.pickme.domain.user.User;
import com.phantom.pickme.dto.UserSignupDto;
import com.phantom.pickme.service.user.UserService;
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

    @Autowired
    private UserService userService;

    @Test
    public void 테스트_회원가입() {
        UserSignupDto dto = UserSignupDto.builder()
                .userCode("asdbd3141cac67asd67abr")
                .id("id")
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
                .bio("bio").build();

        User user = dto.toEntity();

        User savedUser = userService.save(user);

        assertNotNull("Saved user should not be null", user);
        assertEquals(savedUser.id, dto.id);

    }
}
