package com.phantom.pickme.domain.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SkillRepository extends JpaRepository<Skill, SkillId> {

    @Query("SELECT DISTINCT s.skillName " +
            "FROM Skill s " +
            "WHERE s.skillName like concat('%', :keyword, '%')")
    List<String> searchSkillNameByKeyword(@Param("keyword") String keyword);

    @Modifying
    @Query("DELETE FROM Skill s WHERE s.userId = :userId AND s.skillName = :skillName")
    void deleteByUserIdAndMajorName(@Param("userId") String userId, @Param("skillName") String skillName);
}
