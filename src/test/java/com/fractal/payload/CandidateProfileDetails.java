package com.fractal.payload;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandidateProfileDetails{
    public String phoneNumber;

    public String fullName;

    public String email;

    public String address;

    public String linkedIn;

    public String gitHub;

    public String addWebSite;

    List<CandidateEducationDetails> education = new ArrayList<>();

    List<CandidateCertificateDetails> certificates = new ArrayList<>();

//    List<CandidateInternshipDetails> internshipDetails = new ArrayList<>();

//    CandidateEducationDetails candidateEducationDetails;

//    CandidateCertificateDetails candidateCertificateDetails;

    CandidateInternshipDetails internshipDetails;

}
