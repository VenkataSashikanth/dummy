package com.fractal.test;

import com.fractal.endpoints.Endpoints;
import com.fractal.payload.FilePayload;
import com.fractal.utilities.ReadConfig;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class SaveFileTest {
    String fileId;
    Response response;

    ReadConfig readConfig=new ReadConfig();
    @Test(priority = 1)
    public void saveFileTest(){
        readConfig=new ReadConfig();
        String filePath = readConfig.getFile();
        response= Endpoints.saveFile(filePath);
        response.then().log().body();
        if(response.jsonPath().getInt("statusCode")==200 && response.jsonPath().getString("status").equals("OK")){
            System.out.println(readConfig.getFileSaveSuccessMsg());
        }else {
            response.then().log().body();
        }
        response.then().statusCode(200);
        response.then().body("status", equalTo("OK"));
        response.then().time(lessThan(500L));
        Assert.assertEquals(response.jsonPath().getString("message"),readConfig.getFileSaveSuccessMsg());
        Assert.assertNotNull(response.jsonPath().get("data"));

        JSONObject jsonObject=new JSONObject(response.asString());
        boolean isFileNameExisting=jsonObject.getJSONObject("data").has("fileName");
        if (isFileNameExisting){
            Assert.assertTrue(true,"FileName is existing in response");
            String fileName=jsonObject.getJSONObject("data").getString("fileName");
            if (fileName!=null){
                Assert.assertNotNull(response.jsonPath().getString("data.fileName"),"fileName is not getting null");
            }else {
                Assert.fail("FileName is getting null in response");
            }
        }else {
            Assert.fail("FileName is not present in response");
        }
    }

    @Test(priority = 2,dependsOnMethods = "saveFileTest")
    public void testFileId() {
        JSONObject jsonObject=new JSONObject(response.asString());
        boolean isFileIdExisting=jsonObject.getJSONObject("data").has("fileId");
        if (isFileIdExisting){
            Assert.assertTrue(true,"FileId is present in response");
//            fileId=response.jsonPath().getString("data.fileId");
            fileId=jsonObject.getJSONObject("data").getString("fileId");
            System.out.println("This filedId is generated form the response of file/save: "+fileId);
            FilePayload.setFileId(fileId);
            if (fileId != null){
                Assert.assertTrue(true,"fileId is not getting null in response");
            }else {
                Assert.fail("FileId is getting null in response");
            }
        }else {
            Assert.fail("FileId is not present in response");
        }
    }

    @Test(priority = 3,dependsOnMethods = "saveFileTest")
    public void getFileById(){
        String fileId=FilePayload.getFileId();
        System.out.println("This is getFileById test");
        System.out.println(fileId);
        Response response1=Endpoints.getFileById(fileId);
        response1.then().log().body();
    }

    @Test(priority = 4,dependsOnMethods = "saveFileTest")
    public void testIsUploadSuccess(){
        JSONObject jsonObject=new JSONObject(response.asString());
        boolean isUploadSuccessExisting=jsonObject.getJSONObject("data").has("isUploadedSuccess");
        if (isUploadSuccessExisting){
            Assert.assertTrue(true,"isUploadSuccess boolean exists in response");
            Assert.assertTrue(jsonObject.getJSONObject("data").getBoolean("isUploadedSuccess"),"File upload success is true");
        }else {
            Assert.fail("isUploadSuccess boolean is not present in response");
        }
    }

    @Test(priority = 5,dependsOnMethods = "saveFileTest")
    public void testFilePreviewUrl(){
        JSONObject jsonObject=new JSONObject(response.asString());
        boolean isFilePreviewUrlExisting=jsonObject.getJSONObject("data").has("filePreviewUrl");
        if (isFilePreviewUrlExisting){
            Assert.assertTrue(true,"filePreviewUrl exists in response");
            Assert.assertNotNull(jsonObject.getJSONObject("data").getString("filePreviewUrl"));
        }else {
            Assert.fail("File PreviewUrl is not present in response");
        }
    }

    @Test(priority = 6,dependsOnMethods = "saveFileTest")
    public void testFileDownloadUrl(){
        JSONObject jsonObject=new JSONObject(response.asString());
        boolean isFileDownloadUrlExisting=jsonObject.getJSONObject("data").has("fileDownloadUrl");
        if (isFileDownloadUrlExisting){
            Assert.assertTrue(true,"fileDownload Url exists in response");
            Assert.assertNotNull(jsonObject.getJSONObject("data").getString("fileDownloadUrl"));
        }else {
            Assert.fail("File Download Url is not present in response");
        }
    }
}
