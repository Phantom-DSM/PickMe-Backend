package com.phantom.pickme.service.resume;

import com.phantom.pickme.domain.user.User;
import com.phantom.pickme.domain.user.UserRepository;
import com.phantom.pickme.dto.resume.PostSkillRequestDto;
import com.phantom.pickme.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResumeService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getMyProfile(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("No user found with userId '%s'", userId)));
    }

    @Transactional
    public void addSkill(String myUserId, PostSkillRequestDto dto) {
        User user = userRepository.findById(myUserId).orElseThrow(() -> new UserNotFoundException(String.format("No user found with userId '%s'", myUserId)));
        user.addSkill(dto.getSkillName());
        userRepository.save(user);
    }
}
