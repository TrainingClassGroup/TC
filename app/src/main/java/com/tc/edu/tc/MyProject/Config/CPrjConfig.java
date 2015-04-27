package com.tc.edu.tc.MyProject.Config;

import java.io.File;

/**
 * Created by Administrator on 15-4-25.
 */
public class CPrjConfig {
    public static final String CATCH_IMG = "/mnt/sdcard/JiaYouTC/catch/imgs/";


    public static void checkDir(String dirString){
        File dir = new File(dirString);
        if(!dir.exists()){
            dir.mkdirs();
        }
    }

}
