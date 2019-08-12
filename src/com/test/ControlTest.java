package com.test;


import com.control.ControlDateTime;
import com.model.Settings;

import java.io.*;
import java.util.Map;

public class ControlTest {
    private ControlTest(){}
    static{ }
    public static void runTest(){
        File file = null;
        Map<String, String> settings = Settings.getSettings();
        File directory=new File(settings.get("DirServer"));
        if(directory.exists()){
            file = Settings.getFile(settings.get("DirServer")+"\\test_" +new ControlDateTime("dd-MM-yyyy hh-mm").getDateTime()+".txt");
        }
        else{
            file = Settings.getFile("D:\\test_" +new ControlDateTime("dd-MM-yyyy hh-mm").getDateTime()+".txt");
        }

        //set output result tests to text file
        PrintStream outResults = getPrintStreamForAppend(getFile(file.getPath()));
        System.setOut(outResults);

        // run test
        System.out.println("----------------------------------------------------------------------------------------------------------");
        ControlDateTimeTest test = new ControlDateTimeTest();
        test.testFormatDateTime("yyyyMMdd hh mm ss a");
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        ControlScreenShotTest controlScreenShotTest = new ControlScreenShotTest();
        controlScreenShotTest.checkFileScreenShotExist();
        controlScreenShotTest.checkFileScreenShotPlace();
        controlScreenShotTest.checkEqualsFiles();

        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        ControlTeamViewerTest controlTeamViewerTest = new ControlTeamViewerTest();
        controlTeamViewerTest.checkTeamViewerTestExist();
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");


    }



    private static PrintStream getPrintStreamForAppend(File file){
        PrintStream out = null;
        try {
            out = new PrintStream(new FileOutputStream(file, true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return out;
    }

    private static File getFile(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  file;
    }
}