package com.phantom.pickme.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnConfirmedUserRepository extends JpaRepository<UnConfirmedUser, String> {
}
