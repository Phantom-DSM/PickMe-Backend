package com.phantom.pickme.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCodeRepository extends JpaRepository<UserCode, String> {
}
