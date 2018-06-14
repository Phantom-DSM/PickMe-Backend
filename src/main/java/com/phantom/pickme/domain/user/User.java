package com.phantom.pickme.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"enabled", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonIgnore
    private String userId;

    @Column(nullable = false, length = 45, unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "TINYINT NOT NULL DEFAULT 1")
    private Boolean emailOpen = true;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 5, unique = true)
    private Integer stuNum;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(nullable = false, length = 13, unique = true)
    private String phone;

    @Column(nullable = false, columnDefinition = "TINYINT NOT NULL DEFAULT 1")
    private Boolean phoneOpen = true;

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

    @Column
    private Integer desiredMinSal;

    @Column
    private Integer desiredMaxSal;

    @Column(length = 60, unique = true)
    private String profileImgSrc;

    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime modifiedDate;

    @Column
    @JsonIgnore
    private LocalDateTime lastPasswordResetDate;

    @Builder
    public User(String email, String username, String password, String name, Integer stuNum, Sex sex, String phone, int birthDay, int birthMonth, int birthYear, String postNumber, String baseAddr, String detailAddr, String bio, Integer desiredMinSal, Integer desiredMaxSal, String profileImgSrc) {
        this.email = email;
        this.username = username;
        this.password = password;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
