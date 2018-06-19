package com.phantom.pickme.domain.certificate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_certificate")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"userId", "kind", "agency", "result", "grantedDate"})
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long certificateId;

    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    private CertificateKind kind;

    @Column(length = 50)
    private String agency;

    @Column(length = 45)
    private String result;

    @Column
    private LocalDate grantedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

}
