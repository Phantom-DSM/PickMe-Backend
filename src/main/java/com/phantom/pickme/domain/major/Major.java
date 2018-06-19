package com.phantom.pickme.domain.major;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_major")
@IdClass(MajorId.class)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"userId", "majorName"})
public class Major {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "major_name")
    private String majorName;

    @CreationTimestamp
    private LocalDateTime createdDate;

    public Major(String userId, String majorName) {
        this.userId = userId;
        this.majorName = majorName;
    }
}
