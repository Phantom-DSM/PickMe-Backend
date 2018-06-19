package com.phantom.pickme.domain.major;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MajorRepository extends JpaRepository<Major, MajorId> {

    @Query("SELECT DISTINCT m.majorName " +
            "FROM Major m " +
            "WHERE m.majorName like concat('%', :keyword, '%')")
    List<String> searchMajorNameByKeyword(@Param("keyword") String keyword);

    @Modifying
    @Query("DELETE FROM Major m WHERE m.userId = :userId AND m.majorName = :majorName")
    void deleteByUserIdAndMajorName(@Param("userId") String userId, @Param("majorName") String majorName);
}
