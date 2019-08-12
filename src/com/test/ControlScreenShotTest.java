package com.test;

import com.control.*;
import org.junit.Test;
import org.junit.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;
public class ControlScreenShotTest {
    private File fileScreenShot = null;
    private File checkFile = null;
    {
        fileScreenShot =  ControlScreenShot.getScreenShot();
        checkFile =  new File(System.getProperty("user.dir")+"\\pictures\\"+ new ControlDateTime("yyyyMMdd hh mm ss a").getDateTime() + ".jpg");

    }
    @BeforeClass
    public  void setUp() {
        fileScreenShot =  ControlScreenShot.getScreenShot();
        checkFile =  new File(System.getProperty("user.dir")+"\\pictures\\"+ new ControlDateTime("yyyyMMdd hh mm ss a").getDateTime() + ".jpg");
    }
    @Test
    public void checkFileScreenShotExist(){
        System.out.println("Проверка существования файла скриншота");
        try {
            assertNotNull(fileScreenShot);
            System.out.println("файл существует");
        }catch(AssertionError e ){
            System.out.println("Внимание Ошибка файла "+fileScreenShot.getPath()+" нет");
            e.printStackTrace();
        }
    }

    @Test
    public void checkFileScreenShotPlace(){
        System.out.println("Проверка местоположения  файла скриншота");
        try {
            assertNotNull(checkFile);
            System.out.println("файл "+checkFile.getPath()+" существует");
        }catch(AssertionError e ){
            System.out.println("Внимание Ошибка  :файла "+checkFile.getPath()+" нет");
            e.printStackTrace();
        }
    }
    @Test
    public void checkEqualsFiles(){
        System.out.println("Проверка соответствия  файлов");
        try {
            assertEquals(checkFile, fileScreenShot);
            System.out.println("файлы соответствуют");
        }catch(AssertionError e ){
            System.out.println("Внимание Ошибка: файлы  не соответствуют");
            e.printStackTrace();
        }
    }
    @After
    public void tearDown() {
        try {
            boolean result = Files.deleteIfExists(fileScreenShot.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}