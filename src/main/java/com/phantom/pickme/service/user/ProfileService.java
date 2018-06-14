package com.phantom.pickme.service.user;

import com.phantom.pickme.domain.user.User;
import com.phantom.pickme.domain.user.UserRepository;
import com.phantom.pickme.dto.profile.ProfileResponseDto;
import com.phantom.pickme.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public ProfileResponseDto getUserProfile(String userId) {
        return userRepository.readUserProfileById(userId).orElseThrow(() -> new UserNotFoundException(String.format("userId '%s not found", userId)));
    }

    public User getMyProfile(String myUserId) {
        return userRepository.findById(myUserId).orElseThrow(() -> new UserNotFoundException(String.format("userId '%s' not found", myUserId)));
    }

}
