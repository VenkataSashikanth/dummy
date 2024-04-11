package com.fractal.utilities;

import lombok.Getter;

public class Authorization {
    @Getter
    private static String authorizationToken;

    @Getter
    private static boolean isFirstTimeLogin;

    @Getter
    private static boolean isProfileDetailsUpdated;

    @Getter
    private static boolean hasRegisteredDrives;


    public static void setHasRegisteredDrives(boolean hasRegisteredDrives) {
        Authorization.hasRegisteredDrives = hasRegisteredDrives;
    }

    public static void setIsProfileDetailsUpdated(boolean isProfileDetailsUpdated) {
        Authorization.isProfileDetailsUpdated = isProfileDetailsUpdated;
    }

    public static void setIsFirstTimeLogin(boolean isFirstTimeLogin) {
        Authorization.isFirstTimeLogin = isFirstTimeLogin;
    }

    /*public static boolean isFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    public static void setFirstTimeLogin(boolean firstTimeLogin) {
        isFirstTimeLogin = firstTimeLogin;
    }*/

    /*public void setIsFirstTimeLogin(boolean isFirstTimeLogin) {
        Authorization.isFirstTimeLogin = isFirstTimeLogin;
    }*/

    public static void setAuthorizationToken(String authorizationToken) {
        Authorization.authorizationToken = authorizationToken;
    }

}
