package com.phantom.pickme.domain.skill;

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
@Table(name = "user_skill")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"userId", "skillName"})
@IdClass(SkillId.class)
public class Skill {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name = "skill_name")
    @JsonView(View.Resume.class)
    private String skillName;

    @CreationTimestamp
    @JsonView(View.Resume.class)
    private LocalDateTime createdDate;


    public Skill(String userId, String skillName) {
        this.userId = userId;
        this.skillName = skillName;
    }
}
