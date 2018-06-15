package com.phantom.pickme.service.user;

import com.phantom.pickme.domain.user.User;
import com.phantom.pickme.domain.user.UserRepository;
import com.phantom.pickme.dto.profile.*;
import com.phantom.pickme.exception.AlreadyExistsException;
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

    @Transactional
    public void patchDesiredSal(String myUserId, PatchDesiredSalRequestDto dto) {
        int affectedRows = userRepository.patchDesiredSalByUserId(myUserId, dto);
        if (affectedRows == 0) throw new UserNotFoundException(String.format("userId '%s' not found", myUserId));
    }

    @Transactional
    public void patchPhoneNumber(String myUserId, PatchPhoneNumberRequestDto dto) {
        if (userRepository.existsUserByPhone(dto.getPhone()))
            throw new AlreadyExistsException(String.format("phone '%s' already exists", dto.getPhone()));
        int affectedRows = userRepository.patchPhoneNumberByUserId(myUserId, dto);
        if (affectedRows == 0) throw new UserNotFoundException(String.format("userId '%s' not found", myUserId));
    }

    @Transactional
    public void patchBio(String myUserId, PatchBioRequestDto dto) {
        int affectedRows = userRepository.patchBioByUserId(myUserId, dto);
        if (affectedRows == 0) throw new UserNotFoundException(String.format("userId '%s' not found", myUserId));
    }

    @Transactional
    public void patchName(String myUserId, PatchNameRequestDto dto) {
        int affectedRows = userRepository.patchNameByUserId(myUserId, dto);
        if (affectedRows == 0) throw new UserNotFoundException(String.format("userId '%s' not found", myUserId));
    }

    @Transactional
    public void patchEmail(String myUserId, PatchEmailDto dto) {
        if (userRepository.existsUserByEmail(dto.getEmail()))
            throw new AlreadyExistsException(String.format("email '%s' already exists", dto.getEmail()));
        int affectedRows = userRepository.patchEmailByUserId(myUserId, dto);
        if (affectedRows == 0) throw new UserNotFoundException(String.format("userId '%s' not found", myUserId));
    }
}
