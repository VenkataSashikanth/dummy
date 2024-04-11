package com.fractal.payload;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilePayload {

    @Getter
    public static String fileId;

    public static void setFileId(String fileId) {
        FilePayload.fileId = fileId;
    }

}