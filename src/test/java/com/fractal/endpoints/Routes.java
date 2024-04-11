package com.fractal.endpoints;

public class Routes {

//    #BaseURL
//    #Local
//    public static String base_url="http://localhost:9564/api/v1";
//    #QA
    public static String base_url="http://3.110.77.143:9564/api/v1";
//    #Prod
//    public static String base_url="http://13.233.148.88:8085/api/v1";
//    #Pre-Prod
//    public static String base_url="http://3.111.37.114:8085/api/v1";



//    #TestingURLs: Endpoints
//    #File
    public static String save_file_url=base_url+"/file/save";
    public static String get_file_byId_url=base_url+"/file/{fileId}";
    public static String delete_file_byId_url=base_url+"/file/{fileId}";
    public static String preview_file_url=base_url+"/file/preview/{fileId}";
    public static String get_screenShare_video_url=base_url+"/file/get-video";

//    #Candidate
    public static String candidate_sendOtp_url=base_url+"/user/send-otp";
    public static String candidate_validateOtp_url=base_url+"/user/validate-otp";
    public static String candidate_get_basic_details_url=base_url+"/user/get-basic-details";
    public static String save_basicDetails_url=base_url+"/user/save-basic-details";
    public static String update_profile_details_url=base_url+"/user/upload-profile-details";
    public static String get_profile_details_url=base_url+"/user/get-profile-details";
    public static String get_all_drives_of_loggedIn_user_url=base_url+"/drive-config/get-all-drive-of-loggedIn-user";
    public static String user_logOut_url=base_url+"/user/log-out";

}
