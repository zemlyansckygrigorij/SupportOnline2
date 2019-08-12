package com.control;

/**
 * @author Землянский Григорий Михайлович
 * @version 1.7
 *
 * класс для запуска программы TeamViewer
 *
 */
import com.view.*;
import com.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class ControlTeamViewer {
    private static String path = System.getProperty("user.dir");
    private ControlTeamViewer(){}
    public static void runTeamviewer(){
        File file = new File(path +"\\TeamViewerQS.exe");
        if(!file.exists()){
            file = new File(path+"\\files\\TeamViewerQS.exe");
            System.out.println("file - "+file.toPath());
        }
        if(!file.exists()){
            file = new File(path+"\\library\\TeamViewerQS.exe");
        }
        if(!file.exists()){
            path = path+"\\SupportOnline";
            file = new File(path+"\\TeamViewerQS.exe");
        }
        if(!file.exists()){
            path = path+"\\files";
            file = new File(path+"\\TeamViewerQS.exe");
        }
        if(!file.exists()){
            path = Settings.getSettings().get("DirLocal")+"\\EXE\\SupportOnline";
            file = new File(path+"\\TeamViewerQS.exe");
            System.out.println("file - "+file.toPath());
        }
        if(!file.exists()){
            path = Settings.getSettings().get("DirLocal")+"\\EXE\\SupportOnline\\files";
            file = new File(path+"\\TeamViewerQS.exe");
        }
        if(!file.exists()){
            path = Settings.getSettings().get("DirLocal")+"\\EXE\\SupportOnline\\library\\TeamViewerQS.exe";
        }
        if(!file.exists()){
            path = Settings.getSettings().get("DirServer")+"\\SupportOnline";
            file = new File(path+"\\TeamViewerQS.exe");
        }
        if(!file.exists()){
            path = Settings.getSettings().get("DirServer")+"\\SupportOnline\\files";
            file = new File(path+"\\TeamViewerQS.exe");
        }
        if(!file.exists()){
            path = Settings.getSettings().get("DirServer")+"\\UPD";
            file = new File(path+"\\TeamViewerQS.exe");
        }
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(file.getPath());
            pb.start();
            JFrame myWindow = new FrameMessage("<html>Подождите запускается программа<br/> удаленного подключения TeamViewer <br>" +
                    " сообщите тех.поддержке id и пароль</html>");
        }
        catch (IOException e) {
            JFrame myWindow = new FrameException(" <html>Невозможно запустить<br/>программу TeamViewer</html> !!!");
            Settings.writeError(e,"Ошибка заппуска TeamViewer");
            e.printStackTrace();
        }
    }

    //свернуть все окна
    public static void minimazeAllWindows(){
        try {
            Robot rb=new Robot();
            rb.keyPress(KeyEvent.VK_WINDOWS);
            rb.keyPress(KeyEvent.VK_D);
            rb.keyRelease(KeyEvent.VK_WINDOWS);
            rb.keyRelease(KeyEvent.VK_D);
        }
        catch (AWTException e) {

            // JFrame myWindow = new FrameException(" Ошибка AWT !!!");
            Settings.writeError(e,"Программная ошибка запуска AWT");
            e.printStackTrace();
        }
    }
}
