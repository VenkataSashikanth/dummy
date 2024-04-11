package com.fractal.test.logOut;

import com.fractal.endpoints.Endpoints;
import com.fractal.test.Base;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class LogOut extends Base {

    public void userLogout(){
        Response response = Endpoints.logOut();
        if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
            System.out.println("You've been logged out successfully.");
        }else {
            response.then().log().body();
        }
        response.then().statusCode(200);
        response.then().time(lessThan(500L));
        response.then().body("status", equalTo("OK"));
        Assert.assertEquals(response.jsonPath().getString("message"),"You've been logged out successfully.");
        Assert.assertNull(response.jsonPath().get("data"));
    }
}
