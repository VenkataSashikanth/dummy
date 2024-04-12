package com.fractal.test.candidateSignup;

import com.fractal.endpoints.Endpoints;
import com.fractal.payload.CandidatePayload;
import com.fractal.utilities.ReadConfig;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.*;

public class SendOtpTest {

    ReadConfig readConfig=new ReadConfig();
    CandidatePayload candidatePayload;

    @Test(priority = 1)
    public void sendOtpWithEmptyMobileNumber(){
        candidatePayload=new CandidatePayload();
        candidatePayload.setPhoneCode(readConfig.getPhoneCode());
        candidatePayload.setMobileNumber("");
        Response response=Endpoints.sendOTP(candidatePayload);
        if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
            System.out.println("We sent OTP verification code to your mobile number.Please check once...");
        }else {
            response.then().log().body();
        }
        response.then().statusCode(400);
        response.then().time(lessThan(500L));
        response.then().body("status", equalTo("BAD_REQUEST"));
        assertNull(response.jsonPath().get("data"));
        assertEquals(response.jsonPath().getString("message"),"Please Provide Mobile Number");
    }

    @Test(priority = 2)
    public void sendOtpWithInvalidMobileNumber(){
        candidatePayload=new CandidatePayload();
        candidatePayload.setPhoneCode(readConfig.getPhoneCode());
        candidatePayload.setMobileNumber("766093133");
        Response response=Endpoints.sendOTP(candidatePayload);
        if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
            System.out.println("We sent OTP verification code to your mobile number.Please check once...");
        }else {
            response.then().log().body();
        }
        response.then().statusCode(400);
        response.then().time(lessThan(500L));
        response.then().body("status", equalTo("BAD_REQUEST"));
        assertNull(response.jsonPath().get("data"));
        assertEquals(response.jsonPath().getString("message"),"Mobile Number is not valid. Please provide valid mobile number");
    }

    @Test(priority = 3)
    public void sendOtpWithAlphabets(){
        candidatePayload=new CandidatePayload();
        candidatePayload.setPhoneCode(readConfig.getPhoneCode());
        candidatePayload.setMobileNumber("sdfgsdf");
        Response response=Endpoints.sendOTP(candidatePayload);
        if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
            System.out.println("We sent OTP verification code to your mobile number.Please check once...");
        }else {
            response.then().log().body();
        }
        response.then().statusCode(400);
        response.then().time(lessThan(500L));
        response.then().body("status", equalTo("BAD_REQUEST"));
        assertNull(response.jsonPath().get("data"));
        assertEquals(response.jsonPath().getString("message"),"Mobile Number is not valid. Please provide valid mobile number");
    }

//    @Test(priority = 4)
    public void sendOtpTest(){
        candidatePayload=new CandidatePayload();
        candidatePayload.setPhoneCode(readConfig.getPhoneCode());
        candidatePayload.setMobileNumber(readConfig.getCandidateMobileNumber());
        Response response=Endpoints.sendOTP(candidatePayload);
        if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
            System.out.println("We sent OTP verification code to your mobile number.Please check once...");
        }else {
            response.then().log().body();
        }
        response.then().statusCode(200);
        response.then().time(lessThan(500L));
        response.then().body("status", equalTo("OK"));
        assertEquals(response.jsonPath().getString("message"),"We sent OTP verification code to your mobile number.Please check once...");
    }
}
