package com.fractal.test.Dashboard;

import com.fractal.endpoints.Endpoints;
import com.fractal.test.Base;
import com.fractal.utilities.Authorization;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.*;

public class CandidateDrives extends Base {
    Response response;
    int length;

    @Test(priority = 1)
    public void getCandidateRegisteredDrives(){
//        System.out.println(Authorization.isHasRegisteredDrives());
        if (Authorization.isHasRegisteredDrives()){
            response = Endpoints.getAllDrivesOfLoggedInUser();
            if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
                System.out.println("Dear user, your Registered and Completed drives retrieved successfully!!");
            }else {
                response.then().log().body();
            }
            response.then().log().body();
            response.then().statusCode(200);
            response.then().time(lessThan(500L));
            response.then().body("status", equalTo("OK"));
            assertEquals(response.jsonPath().getString("message"),"Data retrieved successfully");

            JSONObject jsonObject=new JSONObject(response.asString());
            length = jsonObject.getJSONArray("data").length();
            if (length>0){
                System.out.println("Total drives registered by logged in User:"+length);
                assertTrue(true,"Candidate has registered for Drives and fetched drives");
            }else {
                fail("Unable to fetch candidate registered drives");
            }
        }else {
            System.out.println("Candidate has not Registered for any Drive....");
        }
    }

    @Test(priority = 2)
    public void testDriveId(){
        JSONObject jsonObject=new JSONObject(response.asString());
        length = jsonObject.getJSONArray("data").length();
        if (length>0){
            assertTrue(true,"Candidate has registered for Drives and fetched drives");
            for(int i=0; i<length; i++){
                boolean isDriveIdExisting = jsonObject.getJSONArray("data").getJSONObject(i).has("driveId");
                if (isDriveIdExisting){
                    assertTrue(true,"DriveId field exists in response");
                    String driveId = jsonObject.getJSONArray("data").getJSONObject(i).getString("driveId");
                    assertNotNull(driveId);
                }else {
                    fail("DriveId field is not exist in response");
                }
            }
        }else {
            fail("Unable to fetch candidate registered drives");
        }
    }

    @Test(priority = 3)
    public void testDriveName(){
        JSONObject jsonObject=new JSONObject(response.asString());
        length = jsonObject.getJSONArray("data").length();
        if (length>0){
            assertTrue(true,"Candidate has registered for Drives and fetched drives");
            for(int i=0; i<length; i++){
                boolean isDriveNameExisting = jsonObject.getJSONArray("data").getJSONObject(i).has("driveName");
                if (isDriveNameExisting){
                    assertTrue(true,"DriveName field exists in response");
                    String driveName = jsonObject.getJSONArray("data").getJSONObject(i).getString("driveName");
                    assertNotNull(driveName);
                }else {
                    fail("DriveName field is not exist in response");
                }
            }
        }else {
            fail("Unable to fetch candidate registered drives");
        }
    }

    @Test(priority = 4)
    public void testAssessmentConfigId(){
        JSONObject jsonObject=new JSONObject(response.asString());
        length = jsonObject.getJSONArray("data").length();
        if (length>0){
            assertTrue(true,"Candidate has registered for Drives and fetched drives");
            for(int i=0; i<length; i++){
                boolean isAssessmentConfigIdExisting = jsonObject.getJSONArray("data").getJSONObject(i).has("assessmentConfigId");
                if (isAssessmentConfigIdExisting){
                    assertTrue(true,"AssessmentConfigId field exists in response");
                    String assessmentConfigId = jsonObject.getJSONArray("data").getJSONObject(i).getString("assessmentConfigId");
                    assertNotNull(assessmentConfigId);
                }else {
                    fail("AssessmentConfigId field is not exist in response");
                }
            }
        }else {
            fail("Unable to fetch candidate registered drives");
        }
    }

    @Test(priority = 5)
    public void testDriveStartTime(){
        JSONObject jsonObject=new JSONObject(response.asString());
        length = jsonObject.getJSONArray("data").length();
        if (length>0){
            assertTrue(true,"Candidate has registered for Drives and fetched drives");
            for(int i=0; i<length; i++){
                boolean isDriveStartTimeExisting = jsonObject.getJSONArray("data").getJSONObject(i).has("startTime");
                if (isDriveStartTimeExisting){
                    assertTrue(true,"Drive Start Time field exists in response");
                    String startTime = jsonObject.getJSONArray("data").getJSONObject(i).getString("startTime");
                    assertNotNull(startTime);
                }else {
                    fail("Drive StartTime field is not exist in response");
                }
            }
        }else {
            fail("Unable to fetch candidate registered drives");
        }
    }

    @Test(priority = 6)
    public void testDriveStartDate(){
        JSONObject jsonObject=new JSONObject(response.asString());
        length = jsonObject.getJSONArray("data").length();
        if (length>0){
            assertTrue(true,"Candidate has registered for Drives and fetched drives");
            for(int i=0; i<length; i++){
                boolean isDriveStartDateExisting = jsonObject.getJSONArray("data").getJSONObject(i).has("startDate");
                if (isDriveStartDateExisting){
                    assertTrue(true,"Drive Start Date field exists in response");
                    String startDate = jsonObject.getJSONArray("data").getJSONObject(i).getString("startDate");
                    assertNotNull(startDate);
                }else {
                    fail("Drive Start Date field is not exist in response");
                }
            }
        }else {
            fail("Unable to fetch candidate registered drives");
        }
    }
}
