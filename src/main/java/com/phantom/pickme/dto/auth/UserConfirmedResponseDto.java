package com.phantom.pickme.dto.auth;

import com.phantom.pickme.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserConfirmedResponseDto {
    private User confirmedUser;
    private String unencodedPassword;

    public UserConfirmedResponseDto(User confirmedUser, String unencodedPassword) {
        this.confirmedUser = confirmedUser;
        this.unencodedPassword = unencodedPassword;
    }
}
