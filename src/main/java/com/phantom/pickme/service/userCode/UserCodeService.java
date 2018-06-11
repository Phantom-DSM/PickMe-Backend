package com.phantom.pickme.service.userCode;

import com.phantom.pickme.domain.user.UserCode;
import com.phantom.pickme.domain.user.UserCodeRepository;
import com.phantom.pickme.exception.UserCodeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserCodeService {
    @Autowired
    private UserCodeRepository userCodeRepository;

    @Transactional(readOnly = true)
    public UserCode getUserCodeInfoFromUserCodeStr(String userCodeStr) throws UserCodeNotFoundException {
        return userCodeRepository.findById(userCodeStr).orElseThrow(() -> new UserCodeNotFoundException("사용자 코드를 찾을 수 없습니다."));
    }

    @Transactional
    public void dropUserCode(String userCodeStr) {
        userCodeRepository.deleteById(userCodeStr);
    }
}
