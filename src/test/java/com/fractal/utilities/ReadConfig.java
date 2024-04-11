package com.fractal.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    Properties properties;
    public ReadConfig(){
        File src=new File("./configuration/config.properties");
        try{
            FileInputStream fis=new FileInputStream(src);
            properties=new Properties();
            properties.load(fis);
        }
        catch (Exception e){
            System.out.println("Exception is "+ e.getMessage());
        }
    }

    public String getFile(){
        return properties.getProperty("FILE");
    }
    public String getFileSaveSuccessMsg(){
        return properties.getProperty("FILE_SAVED_SUCCESS");
    }

    public String getStaticOtp(){
        return properties.getProperty("STATIC_OTP");
    }

    public String getCandidateEmail() {
        return properties.getProperty("CandidateEmail");
    }

    public String getCandidateName(){
        return properties.getProperty("CandidateFullname");
    }

    public String getCandidateMobileNumber() {
        return properties.getProperty("MobileNumber");
    }

    public String getPhoneCode(){
        return properties.getProperty("PhoneCode");
    }

    public String getCandidateExistingEmail(){
        return properties.getProperty("CandidateExistingEmail");
    }
    public String getCandidateAddress(){
        return properties.getProperty("CandidateAddress");
    }
    public String getCandidateDegree(){
        return properties.getProperty("CandidateDegree");
    }
    public String getCandidateSpecialization(){
        return properties.getProperty("CandidateSpecialization");
    }
    public String getCandidateInstituteName(){
        return properties.getProperty("CandidateInstituteName");
    }
    public String getCandidateGraduationYear(){
        return properties.getProperty("CandidateGraduationYear");
    }
    public String getCandidateGpaOrPercentage(){
        return properties.getProperty("CandidateGpaOrPercentage");
    }
}
