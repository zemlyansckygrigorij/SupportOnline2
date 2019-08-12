package com.test;

import com.control.ControlTeamViewer;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class TestExistFiles {
    private String path = System.getProperty("user.dir");
    public void checkAllFilesExist(String path){
        checkFileExist(path + "\\ListError.txt");
        checkFileExist(path + "\\helpAdmin.html");
        checkFileExist(path + "\\localSetting.ini");
        checkFileExist(path + "\\TeamViewerQS.exe");

        checkFileExist(path +"\\lib"+ "\\javax.mail-api-1.4.6.jar");
        checkFileExist(path +"\\lib"+ "\\TeamViewerQS.exe");
        checkFileExist(path +"\\lib"+ "\\TeamViewerQS.exe");
    }

    @Test
    public void checkFileExist(String pathFile){
        File file = new File(pathFile);
        System.out.println("Поиск файла "+pathFile);
        try {
            assertTrue(file.exists());
            System.out.println(pathFile + "найден  ");
        }catch(AssertionError e ){
            System.out.println("Ошибка файл "+ pathFile+" не найден ");
            e.printStackTrace();
        }
    }
}