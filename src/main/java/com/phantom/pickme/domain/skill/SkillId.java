package com.phantom.pickme.domain.skill;

import javax.persistence.Embeddable;
import javax.persistence.IdClass;
import java.io.Serializable;

public class SkillId implements Serializable {
    private String userId;
    private String skillName;
}
