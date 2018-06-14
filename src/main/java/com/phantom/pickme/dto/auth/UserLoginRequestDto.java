package com.phantom.pickme.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Setter
@Getter
public class UserLoginRequestDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
