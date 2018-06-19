package com.phantom.pickme.domain.certificate;

import com.fasterxml.jackson.annotation.JsonView;
import com.phantom.pickme.dto.view.View;
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
@EqualsAndHashCode(of = {"userId", "kind", "name", "agency", "result", "grantedDate"})
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Resume.class)
    private Long certificateId;

    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    @JsonView(View.Resume.class)
    private CertificateKind kind;

    @Column(length = 50)
    @JsonView(View.Resume.class)
    private String name;

    @Column(length = 50)
    @JsonView(View.Resume.class)
    private String agency;

    @Column(length = 45)
    @JsonView(View.Resume.class)
    private String result;

    @Column
    @JsonView(View.Resume.class)
    private LocalDate grantedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    public Certificate(String userId, CertificateKind kind, String name, String agency, String result, LocalDate grantedDate) {
        this.userId = userId;
        this.kind = kind;
        this.name = name;
        this.agency = agency;
        this.result = result;
        this.grantedDate = grantedDate;
    }
}
