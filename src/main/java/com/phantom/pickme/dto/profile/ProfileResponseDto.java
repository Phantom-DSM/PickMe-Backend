package com.phantom.pickme.dto.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.phantom.pickme.domain.user.Sex;
import com.phantom.pickme.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfileResponseDto {

    private String username;

    private String email;

    private String name;

    private Integer stuNum;

    private Sex sex;

    private String phone;

    private int birthDay;

    private int birthMonth;

    private int birthYear;

    private String postNumber;

    private String baseAddr;

    private String detailAddr;

    private String bio;

    private Integer desiredMinSal;

    private Integer desiredMaxSal;

    private String profileImgSrc;

    public ProfileResponseDto(String username, String email, String name, Integer stuNum, Sex sex, String phone, int birthDay, int birthMonth, int birthYear, String postNumber, String baseAddr, String detailAddr, String bio, Integer desiredMinSal, Integer desiredMaxSal, String profileImgSrc) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.stuNum = stuNum;
        this.sex = sex;
        this.phone = phone;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.postNumber = postNumber;
        this.baseAddr = baseAddr;
        this.detailAddr = detailAddr;
        this.bio = bio;
        this.desiredMinSal = desiredMinSal;
        this.desiredMaxSal = desiredMaxSal;
        this.profileImgSrc = profileImgSrc;
    }
}
