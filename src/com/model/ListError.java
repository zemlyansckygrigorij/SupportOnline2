package com.model;
/**
 * @author Землянский Григорий Михайлович
 * @version 1.7
 * класс для формирования списка ошибок из файла ListError.txt
 *
 * */
import com.view.FrameException;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class ListError {
    private Set<String> errors = new HashSet<>();
    private String path = System.getProperty("user.dir");
    public ListError(){
        getListErrorFromFile();
    }
    private void getListErrorFromFile() {
        File file = new File(path+"\\ListError.txt");
        if(!file.exists()){
            path = path+"\\files";
            file = new File(path+"\\ListError.txt");
        }
        if(!file.exists()){

            path = path+"\\SupportOnline";
            file = new File(path+"\\ListError.txt");
        }
        if(!file.exists()){
            path = path+"\\files";
            file = new File(path+"\\ListError.txt");
        }
        if(!file.exists()){
            path = Settings.getSettings().get("DirLocal")+"\\EXE\\SupportOnline";
            file = new File(path+"\\ListError.txt");
        }
        if(!file.exists()){
            path = Settings.getSettings().get("DirServer")+"\\SupportOnline";
            file = new File(path+"\\ListError.txt");
        }
        if(!file.exists()){
            path = Settings.getSettings().get("DirServer")+"\\UPD";
            file = new File(path+"\\ListError.txt");
        }
        try {
            FileInputStream inF = new FileInputStream(path+"\\ListError.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inF,"Cp1251"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                errors.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            // JFrame myWindow = new FrameException(" Отсутствует файл "+path+"\\ListError.txt !!!");
            Settings.writeError(e, " Отсутствует файл "+path+"\\ListError.txt !!!");
            e.printStackTrace();
        }
        catch (IOException e) {
            // JFrame myWindow = new FrameException(" Отсутствует файл  ошибка программы !!!");
            Settings.writeError(e," Программная ошибка ввода вывода ");
            e.printStackTrace();
        }
    }

    public  Set<String>  getListError(){
        return errors;
    }
}
