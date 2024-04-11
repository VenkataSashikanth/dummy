package com.fractal.test.candidateSignup;

import com.fractal.endpoints.Endpoints;
import com.fractal.test.Base;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CandidateBasicDetails extends Base {

    @Test(priority = 1)
    public void getCandidateBasicDetails(){
        Response response = Endpoints.getCandidateBasicDetails();
        if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
            System.out.println("Basic Details Data retrieved successfully");
        }else {
            response.then().log().body();
        }
        response.then().log().body();
        response.then().statusCode(200);
        response.then().time(lessThan(500L));
        response.then().body("status", equalTo("OK"));
        Assert.assertEquals(response.jsonPath().getString("message"),"Data retrieved successfully");
        Assert.assertEquals(response.jsonPath().getString("data.phoneNumber"),readConfig.getPhoneCode()+readConfig.getCandidateMobileNumber());
    }
}
