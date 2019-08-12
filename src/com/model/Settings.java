package com.model;

/**
 * @author Землянский Григорий Михайлович
 * @version 1.7
 *
 * класс для формирования формирования локальных настроек.
 * При загрузке класса формируется Map обьект  переменных
 * полученых из редактора реестра  Windows
 * по пути  Компьютер\HKEY_CURRENT_USER\Software\CRAT-SUCCI
 * в рабочем режиме
 * процедура setSettingWork()
 *
 *
 * или из файла localSetting.ini в текстовом режиме
 *
 * writeClaim(String claim) запись заявки в файл ListClaim.txt
 * getIP() - получить ip- данного компьютера
 * sleep(int time) -засыпание процесса на данное число миллисекунд
 *
 */

import com.control.*;
import com.view.FrameException;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;



public class Settings {

    private static Map<String, String> settings = new HashMap<String, String>();

    static{
        settings.put("UserName",getStringValueFromWinRegistry("UserName"));
        //setSettingTest();
        setSettingWork();
    }
    private static void setSettingWork(){

        settings.put("Agent_repl",getStringValueFromWinRegistry("Agent_repl"));
        settings.put("dBName",getStringValueFromWinRegistry("dBName"));
        settings.put("DirLocal",getStringValueFromWinRegistry("DirLocal"));
        settings.put("DirServer",getStringValueFromWinRegistry("DirServer"));
        settings.put("Email",getStringValueFromWinRegistry("Email"));
        settings.put("EmailFrom",getStringValueFromWinRegistry("EmailFrom"));
        settings.put("EmailTo",getStringValueFromWinRegistry("EmailTo"));
        settings.put("mailSmtpAuth",getStringValueFromWinRegistry("mailSmtpAuth"));
        settings.put("mailSmtpHost",getStringValueFromWinRegistry("mailSmtpHost"));
        settings.put("mailSmtpPort",getStringValueFromWinRegistry("mailSmtpPort"));
        settings.put("mailSmtpStarttlsEnable",getStringValueFromWinRegistry("mailSmtpStarttlsEnable"));
        settings.put("passwordDB",Settings.decodePassword(getStringValueFromWinRegistry("passwordDB")));
        settings.put("password",Settings.decodePassword(getStringValueFromWinRegistry("password")));
        settings.put("Phone",getStringValueFromWinRegistry("Phone"));
        settings.put("port",getStringValueFromWinRegistry("port"));
        settings.put("path",System.getProperty("user.dir"));
        settings.put("Server",getStringValueFromWinRegistry("Server"));
        settings.put("userDataBase",getStringValueFromWinRegistry("userDataBase"));
        settings.put("UserName",getStringValueFromWinRegistry("UserName"));
    }

    private static void setSettingFromWinRegistry(String valueName){
        settings.put(valueName,getStringValueFromWinRegistry(valueName));
    }
    private static String getStringValueFromWinRegistry(String valueName){
        String value = null;
        try {
            value = WinRegistry.readString(
                    WinRegistry.HKEY_CURRENT_USER,                             //HKEY
                    "SOFTWARE\\CRAT-SUCCI",           //Key
                    valueName);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }

    private static void setSettingTest(){
        setSetting(System.getProperty("user.dir")+"\\localSetting.ini");
    }
    private static void setSetting(String path){
        setSettingFromWinRegistry("ListError");
        setSettingFromWinRegistry("Server");
        try {
            FileInputStream inF = new FileInputStream(path);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inF,"Cp1251"));
            String  line= "";
            while ((line = bufferedReader.readLine()) != null ) {
                String[] ary = line.split("=");
                if(ary.length>1){
                    settings.put(ary[0].trim(), ary[1].trim());
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            // JFrame myWindow = new FrameException(" файл "+path+ " не найден !!!");
            Settings.writeError(e," файл "+path+ " не найден !!!");
            e.printStackTrace();
        }
        catch (IOException e) {
            //   JFrame myWindow = new FrameException(" Ошибка связи !!!");
            Settings.writeError(e," Ошибка связи !!!");
            e.printStackTrace();
        }
    }

    public static Map<String, String> getSettings(){
        return  settings;
    }
    public static File getFile(String path){
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                //     JFrame myWindow = new FrameException("Ошибка записи в файл !!!");
                Settings.writeError(e, "Ошибка записи в файл  - "+ path);
                e.printStackTrace();
            }
        }
        return file;
    }
    public static void writeClaim(String claim){
        File f = getFile(System.getProperty("user.dir") +"\\ListClaim.txt");

        try {
            Files.write(Paths.get(f.toPath().toString()), claim.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            //   JFrame myWindow = new FrameException("Ошибка записи в файл !!!");
            Settings.writeError(e,"Ошибка записи в файл " +System.getProperty("user.dir") +"\\ListClaim.txt");
            e.printStackTrace();
        }
    }

    public static void writeError(Exception e,String message){

        Path pathError = Paths.get(System.getProperty("user.dir")+"\\error\\");

        if (!Files.exists(pathError)) {
            try {
                Files.createDirectories(pathError);
            } catch (IOException ex) {
                //   JFrame myWindow = new FrameException(" Ошибка связи !!!");
                ex.printStackTrace();
            }
        }

        String fileNamePrintStackTrace = pathError+"\\"+settings.get("UserName")+"-" +new ControlDateTime("dd-MM-yyyy hh-mm").getDateTime()+"_printStackTrace.txt";
        PrintWriter pwPrintStackTrace= getPrintWriter(fileNamePrintStackTrace);
        e.printStackTrace(pwPrintStackTrace);
        pwPrintStackTrace.close();

        String fileName = pathError+"\\"+settings.get("UserName")+"-" +new ControlDateTime("dd-MM-yyyy hh-mm").getDateTime()+".txt";
        PrintWriter pwPrint = getPrintWriter(fileName);
        pwPrint.print(message);
        e.printStackTrace(pwPrint);
        pwPrintStackTrace.close();

    }
    public static PrintWriter getPrintWriter(String filePath){
        File file = getFile(filePath);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(filePath, true));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return pw;
    }

    public static void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            //  JFrame myWindow = new FrameException("Ошибка прерывания !!!");
            Settings.writeError(e, "Ошибка прерывания ");
            e.printStackTrace();
        }
    }


    public static String decodePassword(String password){
        String[] array = password.split("!!!");
        StringBuilder sb = new StringBuilder(password);
        StringBuilder result = new StringBuilder("");
        String pattern = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789"  ;
        for(int i = 0; i<array.length; i++){
            int codeChar =0;
            if(array[i].length()>2){
                if(isNumeric(array[i])){
                    codeChar = Integer.parseInt(array[i]);
                    codeChar = (codeChar +7)/256;
                    result.append((char)codeChar);
                }
            }else{
                codeChar  = (int) array[i].charAt(0);
                codeChar = codeChar - 49;
                result.append(pattern.charAt(codeChar));
            }

        }


        /* for(int i = 0; i<sb.length()-1; i++){
            char character = sb.charAt(i); // This gives the character 'a'
            int ascii = (int) character;
            if(  ascii>35) {

                ascii = ascii - 49;
                result.append(pattern.charAt(ascii));
               // result.append(';');
            }
        }*/

        return result.toString();
    }
    public static String getIP(){

        String ip = "";
        try {
            ip = getLocalHostLANAddress().toString().replaceAll("/", "");
        } catch (UnknownHostException e) {
            //JFrame myWindow = new FrameException("Неизвестный хост !!!");
            Settings.writeError(e,"Неизвестный хост !!!" );
            e.printStackTrace();
        }
        return ip;
    }
    /**
     * Returns an <code>InetAddress</code> object encapsulating what is most likely the machine's LAN IP address.
     * <p/>
     * This method is intended for use as a replacement of JDK method <code>InetAddress.getLocalHost</code>, because
     * that method is ambiguous on Linux systems. Linux systems enumerate the loopback network interface the same
     * way as regular LAN network interfaces, but the JDK <code>InetAddress.getLocalHost</code> method does not
     * specify the algorithm used to select the address returned under such circumstances, and will often return the
     * loopback address, which is not valid for network communication. Details
     * <a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4665037">here</a>.
     * <p/>
     * This method will scan all IP addresses on all network interfaces on the host machine to determine the IP address
     * most likely to be the machine's LAN address. If the machine has multiple IP addresses, this method will prefer
     * a site-local IP address (e.g. 192.168.x.x or 10.10.x.x, usually IPv4) if the machine has one (and will return the
     * first site-local address if the machine has more than one), but if the machine does not hold a site-local
     * address, this method will return simply the first non-loopback address found (IPv4 or IPv6).
     * <p/>
     * If this method cannot find a non-loopback address using this selection algorithm, it will fall back to
     * calling and returning the result of JDK method <code>InetAddress.getLocalHost</code>.
     * <p/>
     *
     * @throws UnknownHostException If the LAN address of the machine cannot be found.
     */
    private static InetAddress getLocalHostLANAddress() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;
            // Iterate all NICs (network interface cards)...
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // Iterate all IP addresses assigned to each card...
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {

                        if (inetAddr.isSiteLocalAddress()) {
                            // Found non-loopback site-local address. Return it immediately...
                            return inetAddr;
                        }
                        else if (candidateAddress == null) {
                            // Found non-loopback address, but not necessarily site-local.
                            // Store it as a candidate to be returned if site-local address is not subsequently found...
                            candidateAddress = inetAddr;
                            // Note that we don't repeatedly assign non-loopback non-site-local addresses as candidates,
                            // only the first. For subsequent iterations, candidate will be non-null.
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                // We did not find a site-local address, but we found some other non-loopback address.
                // Server might have a non-site-local address assigned to its NIC (or it might be running
                // IPv6 which deprecates the "site-local" concept).
                // Return this non-loopback candidate address...
                return candidateAddress;
            }
            // At this point, we did not find a non-loopback address.
            // Fall back to returning whatever InetAddress.getLocalHost() returns...
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }
            return jdkSuppliedAddress;
        }
        catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException("Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }


    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
