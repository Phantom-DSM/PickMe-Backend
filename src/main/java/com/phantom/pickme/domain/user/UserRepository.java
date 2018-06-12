package com.phantom.pickme.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsUserByEmail(String email);

    Boolean existsUserByUsername(String username);

    Boolean existsUserByPhone(String phone);

    Boolean existsUserByProfileImgSrcAndProfileImgSrcIsNotNull(String profileImgSrc);


}
