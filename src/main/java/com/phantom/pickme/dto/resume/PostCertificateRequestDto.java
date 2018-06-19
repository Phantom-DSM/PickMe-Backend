package com.phantom.pickme.dto.resume;

import com.phantom.pickme.domain.certificate.CertificateKind;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class PostCertificateRequestDto {

    private CertificateKind kind;

    private String name;

    private String agency;

    private String result;

    private LocalDate grantedDate;

}
