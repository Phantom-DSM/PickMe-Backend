package com.phantom.pickme.domain.user;

import lombok.Builder;

public class User {

    String userCode;
    public String id;
    String password;
    String profileImgSrc;
    String phone;
    int birthYear;
    int birthMonth;
    int birthDay;
    String email;
    String postNumber;
    String baseAddr;
    String detailAddr;
    String bio;

    @Builder
    public User(String userCode, String id, String password, String profileImgSrc, String phone, int birthYear, int birthMonth, int birthDay, String email, String postNumber, String baseAddr, String detailAddr, String bio) {
        this.userCode = userCode;
        this.id = id;
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
