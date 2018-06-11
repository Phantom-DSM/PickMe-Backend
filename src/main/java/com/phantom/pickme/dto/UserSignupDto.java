package com.phantom.pickme.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserSignupDto {

    private String userCode;
    private String username;
    private String password;
    private String profileImgSrc;
    private String phone;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private String email;
    private String postNumber;
    private String baseAddr;
    private String detailAddr;
    private String bio;

    @Builder
    public UserSignupDto(String userCode, String username, String password, String profileImgSrc, String phone, int birthYear, int birthMonth, int birthDay, String email, String postNumber, String baseAddr, String detailAddr, String bio) {
        this.userCode = userCode;
        this.username = username;
        this.password = password;
        this.profileImgSrc = profileImgSrc;
        this.phone = phone;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.email = email;
        this.postNumber = postNumber;
        this.baseAddr = baseAddr;
        this.detailAddr = detailAddr;
        this.bio = bio;
    }

}
