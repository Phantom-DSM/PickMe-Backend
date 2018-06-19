package com.phantom.pickme.domain.major;

import com.fasterxml.jackson.annotation.JsonView;
import com.phantom.pickme.dto.view.View;
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
    @JsonView(View.Resume.class)
    private String majorName;

    @CreationTimestamp
    @JsonView(View.Resume.class)
    private LocalDateTime createdDate;

    public Major(String userId, String majorName) {
        this.userId = userId;
        this.majorName = majorName;
    }
}
