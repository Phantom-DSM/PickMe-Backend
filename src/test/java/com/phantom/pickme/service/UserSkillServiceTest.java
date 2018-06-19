package com.phantom.pickme.service;

import com.phantom.pickme.domain.certificate.CertificateKind;
import com.phantom.pickme.domain.user.User;
import com.phantom.pickme.domain.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserSkillServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddSkill() {
        try {
            User u = userRepository.findAll().get(0);
            if (u != null) {
                userRepository.save(userRepository.findById(u.getUserId()).get()
                        .addSkill("스프링 부트")
                        .addMajor("전문분야1")
                        .addCertificate(CertificateKind.CERTIFICATE, "정보기기운용기능사", "산업기술협회", "자격증 취득", LocalDate.now()));
            }
        } catch (IndexOutOfBoundsException e) {

        }
    }
}
