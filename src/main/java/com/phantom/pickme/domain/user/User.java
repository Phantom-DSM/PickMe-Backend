package com.phantom.pickme.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.phantom.pickme.domain.certificate.Certificate;
import com.phantom.pickme.domain.certificate.CertificateKind;
import com.phantom.pickme.domain.major.Major;
import com.phantom.pickme.domain.skill.Skill;
import com.phantom.pickme.dto.view.View;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"enabled", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @JsonView({View.Profile.class, View.Resume.class})
    private String userId;

    @Column(nullable = false, length = 45, unique = true)
    @JsonView({View.Profile.class, View.Resume.class})
    private String email;

    @Column(nullable = false, columnDefinition = "TINYINT NOT NULL DEFAULT 1")
    @JsonView({View.Profile.class, View.Resume.class})
    private Boolean emailOpen = true;

    @Column(nullable = false, length = 100, unique = true)
    @JsonView({View.Profile.class, View.Resume.class})
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, length = 45)
    @JsonView({View.Profile.class, View.Resume.class})
    private String name;

    @Column(nullable = false, length = 5, unique = true)
    @JsonView({View.Profile.class, View.Resume.class})
    private Integer stuNum;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonView({View.Profile.class, View.Resume.class})
    private Sex sex;

    @Column(nullable = false, length = 13, unique = true)
    @JsonView({View.Profile.class, View.Resume.class})
    private String phone;

    @Column(nullable = false, columnDefinition = "TINYINT NOT NULL DEFAULT 1")
    @JsonView({View.Profile.class, View.Resume.class})
    private Boolean phoneOpen = true;

    @Column(nullable = false, length = 2)
    @JsonView({View.Profile.class, View.Resume.class})
    private int birthDay;

    @Column(nullable = false, length = 2)
    @JsonView({View.Profile.class, View.Resume.class})
    private int birthMonth;

    @Column(nullable = false, length = 4)
    @JsonView({View.Profile.class, View.Resume.class})
    private int birthYear;

    @Column(nullable = false, length = 45)
    @JsonView({View.Profile.class, View.Resume.class})
    private String postNumber;

    @Column(nullable = false, length = 45)
    @JsonView({View.Profile.class, View.Resume.class})
    private String baseAddr;

    @Column(nullable = false, length = 45)
    @JsonView({View.Profile.class, View.Resume.class})
    private String detailAddr;

    @Column(nullable = false, length = 100)
    @JsonView({View.Profile.class, View.Resume.class})
    private String bio;

    @Column
    @JsonView({View.Profile.class, View.Resume.class})
    private Integer desiredMinSal;

    @Column
    @JsonView({View.Profile.class, View.Resume.class})
    private Integer desiredMaxSal;

    @Column(length = 60, unique = true)
    @JsonView({View.Profile.class, View.Resume.class})
    private String profileImgSrc;

    @OneToMany(targetEntity = Skill.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonView({View.Resume.class})
    private Set<Skill> skills;

    @OneToMany(targetEntity = Major.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonView({View.Resume.class})
    private Set<Major> majors;

    @OneToMany(targetEntity = Certificate.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonView({View.Resume.class})
    private Set<Certificate> certificates;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @Column
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

    public User addSkill(String skillName) {
        if (skills == null)
            skills = new HashSet<>();
        skills.add(new Skill(userId, skillName));
        return this;
    }

    public User addMajor(String majorName) {
        if (majors == null)
            majors = new HashSet<>();
        majors.add(new Major(userId, majorName));
        return this;
    }

    public User addCertificate(CertificateKind kind, String agency, String result, LocalDate grantedDate) {
        if (certificates == null)
            certificates = new HashSet<>();
        certificates.add(new Certificate(userId, kind, agency, result, grantedDate));
        return this;
    }
}
