package com.phantom.pickme.domain.user;

import com.phantom.pickme.dto.profile.ProfileResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsUserByEmail(String email);

    Boolean existsUserByUsername(String username);

    Boolean existsUserByPhone(String phone);

    Boolean existsUserByProfileImgSrcAndProfileImgSrcIsNotNull(String profileImgSrc);

    @Query("SELECT " +
            "new com.phantom.pickme.dto.profile.ProfileResponseDto(" +
            "u.username, " +
            "(CASE WHEN(u.emailOpen = TRUE) THEN u.email ELSE null END), " +
            "u.name, u.stuNum, u.sex, " +
            "(CASE WHEN(u.phoneOpen = TRUE) THEN u.phone ELSE null END), " +
            "u.birthDay, u.birthMonth, u.birthYear, u.postNumber, u.baseAddr, u.detailAddr, " +
            "u.bio, u.desiredMinSal, u.desiredMaxSal, u.profileImgSrc)" +
            "FROM User u " +
            "WHERE u.userId = :userId")
    Optional<ProfileResponseDto> readUserProfileById(@Param("userId") String userId);
}
