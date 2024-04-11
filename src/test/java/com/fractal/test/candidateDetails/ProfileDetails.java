package com.fractal.test.candidateDetails;

import com.fractal.endpoints.Endpoints;
import com.fractal.payload.CandidateCertificateDetails;
import com.fractal.payload.CandidateEducationDetails;
import com.fractal.payload.CandidateInternshipDetails;
import com.fractal.payload.CandidateProfileDetails;
import com.fractal.test.Base;
import com.fractal.utilities.Authorization;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ProfileDetails extends Base {
    CandidateProfileDetails candidateProfileDetails;
    CandidateEducationDetails education;
    CandidateCertificateDetails certificates;
    CandidateInternshipDetails internshipDetails;

    @Test
    public void updateProfileDetails(){

        System.out.println(Authorization.isProfileDetailsUpdated());

        if (Authorization.isProfileDetailsUpdated()){
            System.out.println("Candidate is not First Time Login and has Already updated both Basic and Profile details");
        }else {
            candidateProfileDetails = new CandidateProfileDetails();
            candidateProfileDetails.setPhoneNumber(readConfig.getPhoneCode()+readConfig.getCandidateMobileNumber());
            candidateProfileDetails.setFullName(readConfig.getCandidateName());
            candidateProfileDetails.setEmail(readConfig.getCandidateEmail());
            candidateProfileDetails.setAddress(readConfig.getCandidateAddress());

            education = new CandidateEducationDetails();
            education.setDegree(readConfig.getCandidateDegree());
            education.setCourseName(readConfig.getCandidateSpecialization());
            education.setSchool(readConfig.getCandidateInstituteName());
            education.setStudiedTill(readConfig.getCandidateGraduationYear());
            education.setGpa(readConfig.getCandidateGpaOrPercentage());

            List<CandidateEducationDetails> educationDetails = new ArrayList<>();
            educationDetails.add(education);
            candidateProfileDetails.setEducation(educationDetails);

            certificates = new CandidateCertificateDetails();
            List<CandidateCertificateDetails> certificateDetails = new ArrayList<>();
            candidateProfileDetails.setCertificates(certificateDetails);


            internshipDetails = new CandidateInternshipDetails();
            /*internshipDetails.setCompany(internshipDetails.getCompany());
            internshipDetails.setStartDate(internshipDetails.getStartDate());
            internshipDetails.setEndDate(internshipDetails.getEndDate());*/
            candidateProfileDetails.setInternshipDetails(internshipDetails);

            System.out.println(candidateProfileDetails);

            Response response = Endpoints.updateProfileDetails(candidateProfileDetails);
            if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
                System.out.println("Dear user, your profile details saved successfully!!");
            }else {
                response.then().log().body();
            }
//            response.then().log().body();
            response.then().statusCode(200);
            response.then().time(lessThan(500L));
            response.then().body("status", equalTo("OK"));
            Assert.assertEquals(response.jsonPath().getString("message"),"Dear user, your profile details saved successfully!!");
            Assert.assertEquals(response.jsonPath().getString("data.phoneNumber"),readConfig.getPhoneCode()+readConfig.getCandidateMobileNumber());
        }
    }
}
