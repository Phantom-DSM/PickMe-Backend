package com.phantom.pickme.service.user;

import com.phantom.pickme.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User save(User user) {
        return user;
    }
}
