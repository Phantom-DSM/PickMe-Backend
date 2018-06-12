package com.phantom.pickme.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UnConfirmedUser {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String userId;

    @ManyToOne(targetEntity = UserCode.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_code")
    private UserCode userCode;


    @Column(nullable = false, length = 45)
    private String email;

    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, length = 13)
    private String phone;

    @Column(nullable = false, length = 2)
    private int birthDay;

    @Column(nullable = false, length = 2)
    private int birthMonth;

    @Column(nullable = false, length = 4)
    private int birthYear;

    @Column(nullable = false, length = 45)
    private String postNumber;

    @Column(nullable = false, length = 45)
    private String baseAddr;

    @Column(nullable = false, length = 45)
    private String detailAddr;

    @Column(nullable = false, length = 100)
    private String bio;

    @Column(length = 60)
    private String profileImgSrc;

    @Builder
    public UnConfirmedUser(UserCode userCode, String email, String username, String password, String phone, int birthDay, int birthMonth, int birthYear, String postNumber, String baseAddr, String detailAddr, String bio, String profileImgSrc) {
        this.userCode = userCode;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.postNumber = postNumber;
        this.baseAddr = baseAddr;
        this.detailAddr = detailAddr;
        this.bio = bio;
        this.profileImgSrc = profileImgSrc;
    }
}
