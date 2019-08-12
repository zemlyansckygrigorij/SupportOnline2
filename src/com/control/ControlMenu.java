package com.control;
/**
 * @author Землянский Григорий Михайлович
 * @version 1.7
 * класс для работы с меню
 *
 * */
import com.test.ControlTest;
import com.view.*;
import com.model.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ControlMenu {
    private static String path = System.getProperty("user.dir");
    public static void getHtml(String url){

        File file = new File(path+"/"+url);
        if(!file.exists()){
            file = new File(path+"/html/"+url);
        }
        if(!file.exists()){
            file = new File(path+"/SupportOnline/html/"+url);
        }

        if(!file.exists()){
            path = Settings.getSettings().get("DirLocal")+"\\EXE\\SupportOnline\\";
            file = new File(path+url);
        }
        if(!file.exists()){
            path = Settings.getSettings().get("DirLocal")+"\\EXE\\SupportOnline\\html\\";
            file = new File(path+url);
        }
        if(!file.exists()){
            path = Settings.getSettings().get("DirServer")+"\\SupportOnline\\";
            file = new File(path+url);
        }
        if(!file.exists()){
            path = Settings.getSettings().get("DirServer")+"\\SupportOnline\\html\\";
            file = new File(path+url);
        }
        if(!file.exists()){
            path = Settings.getSettings().get("DirServer")+"\\UPD\\";
            file = new File(path+url);
        }
        try {
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            e.printStackTrace();
            Settings.writeError(e,"Невозможно найти файл "+file.toPath());
        }
    }
    public static void runTest(){
        ControlTest.runTest();
    }

}
