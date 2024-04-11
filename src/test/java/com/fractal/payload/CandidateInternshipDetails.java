package com.fractal.payload;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandidateInternshipDetails {

    public String id;

    public String company;

    public String startDate;

    public String endDate;

}
