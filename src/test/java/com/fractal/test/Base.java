package com.fractal.test;

import com.fractal.test.candidateSignup.SendOtpTest;
import com.fractal.test.candidateSignup.ValidateOtpTest;
import com.fractal.test.logOut.LogOut;
import com.fractal.utilities.Authorization;
import com.fractal.utilities.ReadConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Base {
    public ReadConfig readConfig=new ReadConfig();
    public Logger logger;
    SendOtpTest sendOtpTest;
    ValidateOtpTest validateOtpTest;
    LogOut logOut;

    @BeforeSuite
    public void setUp(){
        logger= LogManager.getLogger(this.getClass());
        logger.info("*****Send OTP Test Started*****");
        sendOtpTest=new SendOtpTest();
        sendOtpTest.sendOtpTest();
        logger.info("*****Send OTP Test Completed*****");

        logger.info("*****Validate OTP Test Started*****");
        validateOtpTest=new ValidateOtpTest();
        validateOtpTest.validateOtp();
        logger.info("*****Validate OTP Test Completed*****");
        logger.info(Authorization.getAuthorizationToken());
    }

    @AfterSuite
    public void tearDown(){
        logOut=new LogOut();
        logger.info("*****Log out Test Started*****");
        logOut.userLogout();
        logger.info("*****Log out Test Completed*****");
    }
}
