package com.fractal.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandidateEducationDetails {
    public String id;

    public String degree;

    public String courseName;

    public String school;

    public String studiedTill;

    public String gpa;

}
