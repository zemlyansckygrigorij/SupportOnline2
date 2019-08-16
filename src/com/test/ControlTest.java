package com.test;


import com.control.ControlDateTime;
import com.model.Settings;
import com.view.FrameMessage;

import javax.swing.*;
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
        //controlScreenShotTest.checkEqualsFiles();

        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        ControlTeamViewerTest controlTeamViewerTest = new ControlTeamViewerTest();
        controlTeamViewerTest.checkTeamViewerTestExist();
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        ListErrorTest listErrorTest = new ListErrorTest();
        listErrorTest.checkListErrorNotNull();
        listErrorTest.checkSizeListError();
        listErrorTest.checkStringListErrorExist();
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        PropertiesSMTPTest smtpTest = new PropertiesSMTPTest();
        smtpTest.checkSMTPNotNull();
        smtpTest.checkSMTPContainAuth();
        smtpTest.checkSMTPContainStarttls();
        smtpTest.checkSMTPContainHost();
        smtpTest.checkSMTPContainPort();
        smtpTest.checkSMTPContainAuthValue();
        smtpTest.checkSMTPContainStarttlsValue();
        smtpTest.checkSMTPContainHostValue();
        smtpTest.checkSMTPContainPortValue();
        smtpTest.checkSMTPAuthValueEquelsSettingValue();
        smtpTest.checkSMTPStarttlsValueEquelsSettingValue();
        smtpTest.checkSMTPHostValueEquelsSettingValue();
        smtpTest.checkSMTPPortValueEquelsSettingValue();
        smtpTest.netIsAvailable();
        smtpTest.sendTestMessage();
       // smtpTest.checkSMTPParam();
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        SessionEMAILTest sessionTest = new SessionEMAILTest();
        sessionTest.checkSessionEMAILNotNull();
        sessionTest.checkSessionEMAIL();
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        SettingsTest settingTest = new SettingsTest();
        settingTest.checkSettingsNotNull();
        settingTest.checkSettingsAllValues();
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        TestExistFiles testExistFiles = new TestExistFiles();
        //testExistFiles.checkAllFilesExist();
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        UserTest userTest = new UserTest();
        userTest.checkDefaultNameUser();
        userTest.checkDefaultEmailUser();
        userTest.checkDefaultPhoneUser();
        userTest.checkDefaultFullNameUser();
        userTest. checkSetNameUser();
        userTest.checkSetEmailUser();
        userTest.checkSetPhoneUser();
        userTest.checkSetFullNameUser();

        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        ConnectionDBTest connectDBTest = new ConnectionDBTest();
        connectDBTest.checkMySQLProcessExist();
        connectDBTest.checkPingServer();
        connectDBTest.checkServerAndPort();
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");
        System.out.println("\n \n----------------------------------------------------------------------------------------------------------");

        JFrame myWindow = new FrameMessage("<html>Тестирование закончено файл с результатами расположен "+file.getPath()+" </html>");
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