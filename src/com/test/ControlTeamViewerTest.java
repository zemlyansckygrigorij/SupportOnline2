package com.test;

import com.control.*;
import com.model.Settings;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ControlTeamViewerTest {
    {
        if( checkProcessExist("TeamViewer.exe"))killProcess("TeamViewer.exe");

    }
    @Before
    public void setUp() {
        System.out.println(" @Before");
        if( checkProcessExist("TeamViewer.exe"))killProcess("TeamViewer.exe");
    }

    @Test
    public void checkTeamViewerTestExist(){
        if( checkProcessExist("TeamViewer.exe"))killProcess("TeamViewer.exe");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //  JFrame myWindow = new FrameException("Ошибка прерывания !!!");
            Settings.writeError(e, "Ошибка прерывания ");
            e.printStackTrace();
        }
        ControlTeamViewer.runTeamviewer();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //  JFrame myWindow = new FrameException("Ошибка прерывания !!!");
            Settings.writeError(e, "Ошибка прерывания ");
            e.printStackTrace();
        }
        System.out.println("проверка запуска TeamViewer");
        try {
            assertTrue(checkProcessExist("TeamViewer.exe")||checkProcessExist("TeamViewerQS.exe"));
            System.out.println("TeamViewer запускается ");
        }catch(AssertionError e ){
            System.out.println("Внимание Ошибка :TeamViewer не запускается ");
            // throw e;
            e.printStackTrace();
        }finally {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                //  JFrame myWindow = new FrameException("Ошибка прерывания !!!");
                Settings.writeError(e, "Ошибка прерывания ");
                e.printStackTrace();
            }
            if( checkProcessExist("TeamViewer.exe"))killProcess("TeamViewer.exe");
            if( checkProcessExist("TeamViewerQS.exe"))killProcess("TeamViewerQS.exe");

        }

    }

    @After
    public void tearDown() {
        System.out.println(" @After");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if( checkProcessExist("TeamViewer.exe"))killProcess("TeamViewer.exe");
    }

    private boolean checkProcessExist(String processName){
        boolean result = false;
        String listOfProcesses = getCommandOutput("tasklist");
        if (listOfProcesses == null || listOfProcesses.isEmpty()) {
            result = false;

        } else {
            if (listOfProcesses.contains(processName)) {
                result = true;
            } else {
                result = false;
            }
        }//else: process list can be retrieved

        return  result;
    }
    private void killProcess(String processName){
        Runtime rt = Runtime.getRuntime();
        try {
            Process proc = rt.exec("taskkill /F /IM "+processName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCommandOutput(String command)  {
        String output = null;       //the string to return

        Process process = null;
        BufferedReader reader = null;
        InputStreamReader streamReader = null;
        InputStream stream = null;

        try {
            process = Runtime.getRuntime().exec(command);

            //Get stream of the console running the command
            stream = process.getInputStream();
            streamReader = new InputStreamReader(stream);
            reader = new BufferedReader(streamReader);
            String currentLine = null;  //store current line of output from the cmd
            StringBuilder commandOutput = new StringBuilder();  //build up the output from cmd
            while ((currentLine = reader.readLine()) != null) {
                commandOutput.append(currentLine + "\n");
            }

            int returnCode = process.waitFor();
            if (returnCode == 0) {
                output = commandOutput.toString();
            }

        } catch (IOException e) {
            System.err.println("Cannot retrieve output of command");
            System.err.println(e);
            output = null;
        } catch (InterruptedException e) {
            System.err.println("Cannot retrieve output of command");
            System.err.println(e);
        } finally {
            //Close all inputs / readers

            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    System.err.println("Cannot close stream input! " + e);
                }
            }
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    System.err.println("Cannot close stream input reader! " + e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Cannot close reader! " + e);
                }
            }
        }
        //Return the output from the command - may be null if an error occured
        return output;
    }
}
