package com.phantom.pickme.repository;

import com.phantom.pickme.domain.user.Sex;
import com.phantom.pickme.domain.user.UserCode;
import com.phantom.pickme.domain.user.UserCodeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCodeRepositoryTest {

    @Autowired
    private UserCodeRepository userCodeRepository;

    private static String USER_CODE;

    @Before
    public void setup() {
        USER_CODE = userCodeRepository.save(new UserCode("윤찬띵", 30114, Sex.MAN)).getUserCode();
    }

    @Test
    public void 테스트_유저_코드_생성_및_조회() {
        UserCode userCode = userCodeRepository.findById(USER_CODE).orElse(null);

        assertEquals("userCode must be " + USER_CODE, USER_CODE, userCode.getUserCode());
    }


    @After
    public void cleanup() {
        userCodeRepository.deleteAll();
    }
}
