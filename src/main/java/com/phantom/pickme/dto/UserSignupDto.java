package com.phantom.pickme.dto;

import com.phantom.pickme.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserSignupDto {

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
    public UserSignupDto(String userCode, String id, String password, String profileImgSrc, String phone, int birthYear, int birthMonth, int birthDay, String email, String postNumber, String baseAddr, String detailAddr, String bio) {
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

    public User toEntity() {
        return User.builder()
                .userCode(userCode)
                .id(id)
                .password(password)
                .profileImgSrc(profileImgSrc)
                .phone(phone)
                .birthYear(birthYear)
                .birthMonth(birthMonth)
                .birthDay(birthDay)
                .email(email)
                .postNumber(postNumber)
                .baseAddr(baseAddr)
                .detailAddr(detailAddr)
                .bio(bio)
                .build();
    }
}
