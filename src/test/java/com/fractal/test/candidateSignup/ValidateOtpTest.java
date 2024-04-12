package com.fractal.test.candidateSignup;

import com.fractal.endpoints.Endpoints;
import com.fractal.payload.CandidatePayload;
import com.fractal.utilities.Authorization;
import com.fractal.utilities.ReadConfig;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.*;

public class ValidateOtpTest {
    String authorizationToken;
    ReadConfig readConfig=new ReadConfig();
    CandidatePayload candidatePayload;

    @Test(priority = 1)
    public void validateOtpWithoutMobileNumber(){
        candidatePayload=new CandidatePayload();
        candidatePayload.setPhoneCode("+91");
        candidatePayload.setMobileNumber("");
        candidatePayload.setOTP(readConfig.getStaticOtp());
        Response response= Endpoints.validateOTP(candidatePayload);
        if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
            System.out.println("Your Otp  verified successfully !! , Welcome to f360 :)");
        }else {
            response.then().log().body();
        }
        response.then().statusCode(400);
        response.then().time(lessThan(500L));
        response.then().body("status", equalTo("BAD_REQUEST"));
        assertNull(response.jsonPath().get("data"));
    }

    @Test(priority = 2)
    public void validateOtpChangingMobileNumber(){
        candidatePayload=new CandidatePayload();
        candidatePayload.setPhoneCode("+91");
        candidatePayload.setMobileNumber("9999999999");
        candidatePayload.setOTP(readConfig.getStaticOtp());
        Response response= Endpoints.validateOTP(candidatePayload);
        if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
            System.out.println("Your Otp  verified successfully !! , Welcome to f360 :)");
        }else {
            response.then().log().body();
        }
        response.then().statusCode(400);
        response.then().time(lessThan(500L));
        response.then().body("status", equalTo("BAD_REQUEST"));
        assertNull(response.jsonPath().get("data"));
    }

    @Test(priority = 3)
    public void validateOtpWithIncorrectOtp(){
        candidatePayload=new CandidatePayload();
        candidatePayload.setPhoneCode("+91");
        candidatePayload.setMobileNumber(readConfig.getCandidateMobileNumber());
        candidatePayload.setOTP("poj");
        Response response= Endpoints.validateOTP(candidatePayload);
        if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
            System.out.println("Your Otp  verified successfully !! , Welcome to f360 :)");
        }else {
            response.then().log().body();
        }
        response.then().statusCode(400);
        response.then().time(lessThan(500L));
        response.then().body("status", equalTo("BAD_REQUEST"));
        assertNull(response.jsonPath().get("data"));
        assertEquals(response.jsonPath().getString("message"),"Invalid OTP, Please check once......");
    }

    @Test(priority = 4)
    public void validateOtpWithEmpty(){
        candidatePayload=new CandidatePayload();
        candidatePayload.setPhoneCode("+91");
        candidatePayload.setMobileNumber(readConfig.getCandidateMobileNumber());
        candidatePayload.setOTP("");
        Response response= Endpoints.validateOTP(candidatePayload);
        if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
            System.out.println("Your Otp  verified successfully !! , Welcome to f360 :)");
        }else {
            response.then().log().body();
        }
        response.then().statusCode(400);
        response.then().time(lessThan(500L));
        response.then().body("status", equalTo("BAD_REQUEST"));
        assertNull(response.jsonPath().get("data"));
        assertEquals(response.jsonPath().getString("message"),"Invalid OTP, Please check once......");
    }

//    @Test(priority = 5)
    public void validateOtp(){
        candidatePayload=new CandidatePayload();
        candidatePayload.setPhoneCode("+91");
        candidatePayload.setMobileNumber(readConfig.getCandidateMobileNumber());
        candidatePayload.setOTP(readConfig.getStaticOtp());
        Response response= Endpoints.validateOTP(candidatePayload);
        if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
            System.out.println("Your Otp  verified successfully !! , Welcome to f360 :)");
        }else {
            response.then().log().body();
        }
//        response.then().log().body();
        response.then().statusCode(200);
        response.then().time(lessThan(2000L));
        response.then().body("status", equalTo("OK"));
        assertEquals(response.jsonPath().getString("message"),"Your Otp  verified successfully !! , Welcome to f360 :)");
        authorizationToken=response.jsonPath().getString("data.authToken");
        Authorization.setAuthorizationToken(authorizationToken);
        assertEquals(response.jsonPath().getString("data.phoneNumber"),readConfig.getPhoneCode()+readConfig.getCandidateMobileNumber());

        boolean isFirstTimeLogin = response.jsonPath().getBoolean("data.isFirstTimeLogin");
        Authorization.setIsFirstTimeLogin(isFirstTimeLogin);

        boolean isProfileDetailsUpdated = response.jsonPath().getBoolean("data.isProfileDetailsUpdated");
        Authorization.setIsProfileDetailsUpdated(isProfileDetailsUpdated);

        boolean hasRegisteredDrives = response.jsonPath().getBoolean("data.hasRegisteredDrives");
        Authorization.setHasRegisteredDrives(hasRegisteredDrives);

        assertEquals(response.jsonPath().getString("data.userRole"),"CANDIDATE");
    }
}
