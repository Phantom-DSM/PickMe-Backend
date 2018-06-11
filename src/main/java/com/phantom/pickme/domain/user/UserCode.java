package com.phantom.pickme.domain.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity(name = "user_code_tbl")
@NoArgsConstructor
@Getter
@Setter
public class UserCode {

    @Id
    @Column(name = "user_code")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String userCode;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 5, unique = true)
    private Integer stuNum;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    public UserCode(String name, Integer stuNum, Sex sex) {
        this.name = name;
        this.stuNum = stuNum;
        this.sex = sex;
    }
}
