package com.phantom.pickme.domain.certificate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    @Modifying
    @Query("DELETE FROM Certificate c WHERE c.userId = :userId AND c.certificateId = :certificateId")
    void deleteByUserIdAndCertificateId(@Param("userId") String myUserId, @Param("certificateId") Long certificateId);
}
