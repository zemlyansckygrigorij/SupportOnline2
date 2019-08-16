package com.test;

import com.model.ConnectionDB;
import com.model.Settings;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.lang.Runtime;
import static org.junit.Assert.assertTrue;

public class ConnectionDBTest {
    Map<String, String> settings = Settings.getSettings();
    @Test
    public void checkPingServer() {

        System.out.println("Попытка пропинговать сервер ");

        try {
            boolean reachable = (Runtime.getRuntime().exec(settings.get("Server")).waitFor()==0);
            assertTrue(reachable);
            System.out.println("сервер пингуется ");
        }catch (AssertionError  e ){
            System.out.println("Ошибка сервер не пингуется ");
        } catch (InterruptedException e) {
            System.out.println("Ошибка сервер не пингуется внезапное прерывание");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ошибка сервер не пингуется проблемы с вводом выводом");
            e.printStackTrace();
        }
    }
    @Test
    public void checkMySQLProcessExist(){
    System.out.println("проверка наличия процесса mysqld.exe");
    try {
        assertTrue(checkProcessExist("mysqld.exe"));
        System.out.println("mysqld.exe запущен ");
    }catch (AssertionError  e ){
        System.out.println("Ошибка процесс mysqld.exe не запущен ");
    }
    System.out.println("проверка наличия процесса MySQLNotifier.exe");
    try {
        assertTrue(checkProcessExist("MySQLNotifier.exe"));
        System.out.println("MySQLNotifier.exe запущен ");
    }catch (AssertionError e  ){
        System.out.println("Ошибка процесс MySQLNotifier.exe не запущен ");
    }
}

    @Test
    public void checkServerAndPort()
    {
        System.out.println("проверка хоста и  порта ");

        try {
            assertTrue(serverListening(settings.get("Server"), Integer.parseInt(settings.get("port"))));
            System.out.println("все в порядке");
        }catch (AssertionError e  ){
            System.out.println("проверьте хост  "+ settings.get("Server")+"и порт "+settings.get("port"));
        }
        // Rest of test...
    }

    @Test
    public void TestDBConnection(){
        String dbName = settings.get("dBName");
        String userName  = settings.get("userDataBase");
        String host = settings.get("Server");
        String port = settings.get("port");
        String password = settings.get("passwordDB");
        System.out.println("проверка соединения с базой данных ");
        try {
            System.out.println("Проверка mysql-connector-java.jar");
            Class.forName("com.mysql.jdbc.Driver");}
        catch (ClassNotFoundException e) {
            System.out.println("Ошибка : Проверить наличие файла mysql-connector-java.jar");
        }
        try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbName+ "?autoReconnect="+"true"+"&useSSL="+"true"   , userName, password);
        } catch (SQLException e) {

            System.out.println("Ошибка подключения к базе данных");
            e.printStackTrace();
        }
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



    public static boolean serverListening(String host, int port)
    {
        Socket s = null;
        try
        {
            s = new Socket(host, port);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
        finally
        {
            if(s != null)
                try {s.close();}
                catch(Exception e){}
        }
    }
}
