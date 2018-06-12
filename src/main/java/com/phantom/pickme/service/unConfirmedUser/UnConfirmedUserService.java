package com.phantom.pickme.service.unConfirmedUser;

import com.phantom.pickme.domain.user.UnConfirmedUser;
import com.phantom.pickme.domain.user.UnConfirmedUserRepository;
import com.phantom.pickme.exception.ConfirmCodeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UnConfirmedUserService {

    @Autowired
    private UnConfirmedUserRepository unConfirmedUserRepository;

    @Transactional
    public void dropUnConfirmedUser(String userId) {
        unConfirmedUserRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public UnConfirmedUser findUnConfirmedUserByConfirmCode(String confirmCode) {
        return unConfirmedUserRepository.findById(confirmCode).orElseThrow(() -> new ConfirmCodeNotFoundException(String.format("invalid confirm code '%s'", confirmCode)));
    }

    public void save(UnConfirmedUser user) {
        unConfirmedUserRepository.save(user);
    }
}
