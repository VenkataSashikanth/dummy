package com.fractal.test;

import com.fractal.endpoints.Endpoints;
import com.fractal.payload.FilePayload;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetFileByIdTest {

    String fileId;

    @Test
    public void testGetFileById(){
        if (FilePayload.getFileId()!=null){
            fileId=FilePayload.getFileId();
        }else {
            fileId="64e441ca6266c713c265d316";
        }
        System.out.println("This is getFileById test");
        System.out.println(fileId);
        Response response=Endpoints.getFileById(fileId);
        response.then().log().body();
    }
}
