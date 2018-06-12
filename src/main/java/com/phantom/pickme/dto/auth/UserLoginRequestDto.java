package com.phantom.pickme.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserLoginRequestDto {
    private String username;
    private String password;
}
