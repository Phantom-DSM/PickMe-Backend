package com.phantom.pickme.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserLoginDto {
    private String username;
    private String password;
}
