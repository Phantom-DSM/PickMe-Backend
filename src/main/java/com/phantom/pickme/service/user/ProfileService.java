package com.phantom.pickme.service.user;

import com.phantom.pickme.domain.user.User;
import com.phantom.pickme.domain.user.UserRepository;
import com.phantom.pickme.dto.profile.PatchAddressRequestDto;
import com.phantom.pickme.dto.profile.PatchBirthRequestDto;
import com.phantom.pickme.dto.profile.ProfileResponseDto;
import com.phantom.pickme.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public ProfileResponseDto getUserProfile(String userId) {
        return userRepository.readUserProfileById(userId).orElseThrow(() -> new UserNotFoundException(String.format("userId '%s not found", userId)));
    }

    @Transactional(readOnly = true)
    public User getMyProfile(String myUserId) {
        return userRepository.findById(myUserId).orElseThrow(() -> new UserNotFoundException(String.format("userId '%s' not found", myUserId)));
    }

    @Transactional
    public void toggleEmailPrivacy(String myUserId) {
        int affectedRows = userRepository.toggleEmailPrivacy(myUserId);
        if (affectedRows == 0) throw new UserNotFoundException(String.format("userId '%s' not found", myUserId));
    }

    @Transactional
    public void togglePhonePrivacy(String myUserId) {
        int affectedRows = userRepository.togglePhonePrivacy(myUserId);
        if (affectedRows == 0) throw new UserNotFoundException(String.format("userId '%s' not found", myUserId));
    }

    @Transactional
    public void patchBirth(String myUserId, PatchBirthRequestDto dto) {
        int affectedRows = userRepository.patchBirthByUserId(myUserId, dto);
        if (affectedRows == 0) throw new UserNotFoundException(String.format("userId '%s' not found", myUserId));
    }

    @Transactional
    public void patchAddress(String myUserId, PatchAddressRequestDto dto) {
        int affectedRows = userRepository.patchAddressByUserId(myUserId, dto);
        if (affectedRows == 0) throw new UserNotFoundException(String.format("userId '%s' not found", myUserId));
    }
}
