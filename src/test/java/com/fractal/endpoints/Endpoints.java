package com.fractal.endpoints;

import com.fractal.payload.*;
import com.fractal.utilities.Authorization;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.*;

public class Endpoints {

    static String authorizationToken;

    public static Response sendOTP(CandidatePayload candidatePayload){
        return given()
                .contentType(ContentType.JSON)
                .body(candidatePayload)
                .when()
                .post(Routes.candidate_sendOtp_url);
    }

    public static Response validateOTP(CandidatePayload candidatePayload){
        return given()
                .contentType(ContentType.JSON)
                .body(candidatePayload)
                .when()
                .post(Routes.candidate_validateOtp_url);
    }

    public static Response getCandidateBasicDetails(){
        authorizationToken=Authorization.getAuthorizationToken();
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+authorizationToken)
                .when()
                .get(Routes.candidate_get_basic_details_url);
    }

    public static Response saveBasicDetails(CandidateDetails candidateDetails){
        authorizationToken=Authorization.getAuthorizationToken();
        return given()
                .header("Authorization","Bearer "+authorizationToken)
                .contentType(ContentType.JSON)
                .body(candidateDetails)
                .when()
                .post(Routes.save_basicDetails_url);
    }

    public static Response updateProfileDetails(CandidateProfileDetails candidateProfileDetails){
        authorizationToken=Authorization.getAuthorizationToken();
        return given()
                .header("Authorization","Bearer "+authorizationToken)
                .contentType(ContentType.JSON)
                .body(candidateProfileDetails)
                .when()
                .post(Routes.update_profile_details_url);
    }

    public static Response getAllDrivesOfLoggedInUser(){
        authorizationToken=Authorization.getAuthorizationToken();
        return given()
                .header("Authorization","Bearer "+authorizationToken)
                .contentType(ContentType.JSON)
                .when()
                .get(Routes.get_all_drives_of_loggedIn_user_url);
    }

    public static Response saveFile(String file){
        return given()
                .contentType(ContentType.MULTIPART)
                .multiPart("file", new File(file))
                .body(file)
                .when()
                .post(Routes.save_file_url);
    }

    public static Response getFileById(String fileId){
        return given()
                .contentType(ContentType.MULTIPART)
                .pathParam("fileId",fileId)
                .when()
                .get(Routes.get_file_byId_url);
    }

    public static Response logOut(){
        authorizationToken=Authorization.getAuthorizationToken();
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer "+authorizationToken)
                .when()
                .get(Routes.user_logOut_url);
    }
}
