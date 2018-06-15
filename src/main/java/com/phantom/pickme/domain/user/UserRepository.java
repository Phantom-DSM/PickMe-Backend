package com.phantom.pickme.domain.user;

import com.phantom.pickme.dto.profile.PatchAddressRequestDto;
import com.phantom.pickme.dto.profile.PatchBirthRequestDto;
import com.phantom.pickme.dto.profile.ProfileResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query("UPDATE User u " +
            "SET u.emailOpen = CASE WHEN(u.emailOpen = TRUE) THEN FALSE ELSE TRUE END " +
            "WHERE u.userId = :userId")
    int toggleEmailPrivacy(@Param("userId") String userId);

    @Modifying
    @Query("UPDATE User u " +
            "SET u.phoneOpen = CASE WHEN(u.phoneOpen = TRUE) THEN FALSE ELSE TRUE END " +
            "WHERE u.userId = :userId")
    int togglePhonePrivacy(@Param("userId") String userId);

    @Modifying
    @Query("UPDATE User u " +
            "SET u.birthDay = :#{#dto.birthDay}, u.birthMonth = :#{#dto.birthMonth}, u.birthYear = :#{#dto.birthYear} " +
            "WHERE u.userId = :userId")
    int patchBirthByUserId(@Param("userId") String userId, @Param("dto") PatchBirthRequestDto dto);

    @Modifying
    @Query("UPDATE User u " +
            "SET u.postNumber = :#{#dto.postNumber}, u.baseAddr = :#{#dto.baseAddr}, u.detailAddr = :#{#dto.detailAddr} " +
            "WHERE u.userId = :userId")
    int patchAddressByUserId(@Param("userId") String userId, @Param("dto") PatchAddressRequestDto dto);
}
