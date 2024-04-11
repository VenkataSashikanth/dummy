package com.fractal.payload;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandidateCertificateDetails {

    public String id;

    public String certificateName;

    public String issueDate;

    public String issuingAuthority;

}
