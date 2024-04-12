package com.fractal.test.candidateDetails;

import com.fractal.endpoints.Endpoints;
import com.fractal.payload.CandidateDetails;
import com.fractal.test.Base;
import com.fractal.utilities.Authorization;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.*;

public class BasicDetails extends Base {
    CandidateDetails candidateDetails;
//    boolean isFirstTimeLogin = Authorization.isFirstTimeLogin();

    @Test(priority = 1)
    public void saveBasicDetailsWithoutEmail(){
        if (Authorization.isFirstTimeLogin()){
            candidateDetails=new CandidateDetails();
            candidateDetails.setFullName(readConfig.getCandidateName());
            Response response = Endpoints.saveBasicDetails(candidateDetails);
            if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
                System.out.println("Your Basic Details updated successfully");
            }else {
                response.then().log().body();
            }
            response.then().statusCode(400);
            response.then().time(lessThan(500L));
            response.then().body("status", equalTo("BAD_REQUEST"));
            assertEquals(response.jsonPath().getString("message"),"Please enter email");
            assertNull(response.jsonPath().get("data"));
        }else {
            System.out.println("Candidate is not First Time Login and has Already updated his Basic details");
        }
    }

    @Test(priority = 2)
    public void saveBasicDetailsWithoutName(){
        if (Authorization.isFirstTimeLogin()){
            candidateDetails=new CandidateDetails();
            candidateDetails.setEmail(readConfig.getCandidateEmail());
            Response response = Endpoints.saveBasicDetails(candidateDetails);
            if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
                System.out.println("Your Basic Details updated successfully");
            }else {
                response.then().log().body();
            }
            response.then().statusCode(400);
            response.then().time(lessThan(500L));
            response.then().body("status", equalTo("BAD_REQUEST"));
            assertEquals(response.jsonPath().getString("message"),"Please enter full name");
            assertNull(response.jsonPath().get("data"));
        }else {
            System.out.println("Candidate is not First Time Login and has Already updated his Basic details");
        }
    }

    @Test(priority = 3)
    public void saveBasicDetailsWithExistingMail(){
        if (Authorization.isFirstTimeLogin()){
            candidateDetails=new CandidateDetails();
            candidateDetails.setEmail(readConfig.getCandidateExistingEmail());
            candidateDetails.setFullName(readConfig.getCandidateName());
            Response response = Endpoints.saveBasicDetails(candidateDetails);
            if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
                System.out.println("Your Basic Details updated successfully");
            }else {
                response.then().log().body();
            }
            response.then().statusCode(400);
            response.then().time(lessThan(500L));
            response.then().body("status", equalTo("BAD_REQUEST"));
            assertEquals(response.jsonPath().getString("message"),"Email already registered");
            assertNull(response.jsonPath().get("data"));
        }else {
            System.out.println("Candidate is not First Time Login and has Already updated his Basic details");
        }
    }

    @Test(priority = 4)
    public void saveBasicDetailsWithInvalidMail(){
        if (Authorization.isFirstTimeLogin()){
            candidateDetails=new CandidateDetails();
            candidateDetails.setEmail("sashiInvalidMail");
            candidateDetails.setFullName(readConfig.getCandidateName());
            Response response = Endpoints.saveBasicDetails(candidateDetails);
            if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
                System.out.println("Your Basic Details updated successfully");
            }else {
                response.then().log().body();
            }
            response.then().statusCode(400);
            response.then().time(lessThan(500L));
            response.then().body("status", equalTo("BAD_REQUEST"));
            assertEquals(response.jsonPath().getString("message"),"Invalid email pattern! Please check once....");
            assertNull(response.jsonPath().get("data"));
        }else {
            System.out.println("Candidate is not First Time Login and has Already updated his Basic details");
        }
    }

    @Test(priority = 5)
    public void saveBasicDetails(){
        if (Authorization.isFirstTimeLogin()){
            candidateDetails=new CandidateDetails();
            candidateDetails.setEmail(readConfig.getCandidateEmail());
            candidateDetails.setFullName(readConfig.getCandidateName());
            Response response = Endpoints.saveBasicDetails(candidateDetails);
            if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
                System.out.println("Your Basic Details updated successfully");
            }else {
                response.then().log().body();
            }
//            response.then().log().body();
            response.then().statusCode(200);
            response.then().time(lessThan(500L));
            response.then().body("status", equalTo("OK"));
            assertEquals(response.jsonPath().getString("message"),"Data saved successfully");
            assertEquals(response.jsonPath().getString("data.phoneNumber"),readConfig.getPhoneCode()+readConfig.getCandidateMobileNumber());
            assertEquals(response.jsonPath().getString("data.fullName"),readConfig.getCandidateName());
            assertEquals(response.jsonPath().getString("data.email"),readConfig.getCandidateEmail());
        }else {
            System.out.println("Candidate is not First Time Login and has Already updated his Basic details");
        }
    }
}
