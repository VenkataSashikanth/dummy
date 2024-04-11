package com.fractal.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePayload {
    public String mobileNumber;
    public String phoneCode;
    public String OTP;
}
