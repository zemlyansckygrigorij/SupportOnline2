package com.control;

/**
 * @author Землянский Григорий Михайлович
 * @version 1.7
 *
 * класс для создания скриншота экрана, создания файла картинки
 * и сохранения его в папке "pictures"
 */
import com.view.*;
import com.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.*;
import java.util.*;


public class ControlScreenShot {
    private static Map<String, String> settings = Settings.getSettings();
    private static String pathDirServer = settings.get("DirServer");
  //  private static String path = System.getProperty("user.dir");
    private static Path pathPictures = null;//Paths.get(path+"\\pictures\\");
    private static String pictureName = "";
    static {
        File directory=new File(pathDirServer);
        if(!directory.exists()){
            directory = new File(System.getProperty("user.dir"));
        }
        pathPictures = Paths.get(directory +"\\pictures\\");
        if(!new File(pathPictures.toString()).exists()) {
            try {
                Files.createDirectories(pathPictures);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //
    public static File getScreenShot(){
        checkFolderPictures();
        Robot robot = null;
        File image =  new File(pathPictures+"\\"+getPictureName());
        try {
            robot = new Robot();
        } catch (AWTException e) {
            //   JFrame myWindow = new FrameException(" Ошибка программы !!!");
            Settings.writeError(e, "Проверьте файл "+pathPictures+getPictureName());
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ALT);

        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        robot.keyRelease(KeyEvent.VK_ALT);
        try {

            ImageIO.write(screenShot, "JPG", image);
        } catch (IOException e) {
            // JFrame myWindow = new FrameException(" Ошибка связи !!!");
            Settings.writeError(e,"Проверьте файл "+pathPictures+getPictureName());
            e.printStackTrace();

        }
        return image;
    }
    //проверить наличие папки pictures если нет то создается
    private static void checkFolderPictures(){
        if (!Files.exists(pathPictures)) {
            try {
                Files.createDirectories(pathPictures);
            } catch (IOException e) {
                //     JFrame myWindow = new FrameException(" Ошибка связи !!!");
                Settings.writeError(e,"Невозможно создать директорию "+pathPictures);
                e.printStackTrace();
            }
        }
    }
    // получить имя создаваемой картинки
    private static String getPictureName(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");
        Calendar now = Calendar.getInstance();
        pictureName =  formatter.format(now.getTime())+".jpg";
        return pictureName;
    }
}
