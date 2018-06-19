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
        User a = userRepository.findByUsername("nooheat1228").get();

        userRepository.save(userRepository.findById("402881e463fc0bff0163fc0e30920005").get()
                .addSkill("스프링 부트")
                .addMajor("전문분야1")
                .addCertificate(CertificateKind.CERTIFICATE, "산업기술협회", "자격증 취득", LocalDate.now()));
    }
}
