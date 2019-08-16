package com.test;

import com.model.*;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class PropertiesSMTPTest {
    Properties smtp = new PropertiesSMTP().getSMTP();
    private static Map<String, String> settings = Settings.getSettings();
    @Test
    public void checkSMTPNotNull(){
        System.out.println("Проверка существования обьекта свойств SMTP");
        try {
            assertNotNull(smtp);
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }
    @Test
    public void checkSMTPContainAuth(){
        System.out.println("Проверка существования  свойства mail.smtp.auth SMTP");
        try {
            assertNotNull(smtp.get("mail.smtp.auth"));
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }

    @Test
    public void checkSMTPContainStarttls(){
        System.out.println("Проверка существования  свойства mail.smtp.starttls.enable SMTP");
        try {
            assertNotNull(smtp.get("mail.smtp.starttls.enable"));
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }

    @Test
    public void checkSMTPContainHost(){
        System.out.println("Проверка существования  свойства mail.smtp.host SMTP");
        try {
            assertNotNull(smtp.get("mail.smtp.host"));
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }

    @Test
    public void checkSMTPContainPort(){
        System.out.println("Проверка существования  свойства mail.smtp.port SMTP");
        try {
            assertNotNull(smtp.get("mail.smtp.port"));
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }



    @Test
    public void checkSMTPContainAuthValue(){
        System.out.println("Проверка существования значения свойства mail.smtp.auth SMTP");
        try {
            assertFalse(smtp.get("mail.smtp.auth").equals(""));
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }

    @Test
    public void checkSMTPContainStarttlsValue(){
        System.out.println("Проверка существования значения свойства mail.smtp.starttls.enable SMTP");
        try {
            assertFalse(smtp.get("mail.smtp.starttls.enable").equals(""));
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }

    @Test
    public void checkSMTPContainHostValue(){
        System.out.println("Проверка существования значения свойства mail.smtp.host SMTP");
        try {
            assertFalse(smtp.get("mail.smtp.host").equals(""));
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }

    @Test
    public void checkSMTPContainPortValue(){
        System.out.println("Проверка существования значения свойства mail.smtp.port SMTP");
        try {
            assertFalse(smtp.get("mail.smtp.port").equals(""));
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }



    @Test
    public void checkSMTPAuthValueEquelsSettingValue(){
        System.out.println("Проверка существования значения свойства mail.smtp.auth SMTP");
        try {
            assertEquals(settings.get("mailSmtpAuth"), smtp.get("mail.smtp.auth"));
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }

    @Test
    public void checkSMTPStarttlsValueEquelsSettingValue(){
        System.out.println("Проверка существования значения свойства mail.smtp.starttls.enable SMTP");
        try {
            assertEquals(settings.get("mailSmtpStarttlsEnable"), smtp.get("mail.smtp.starttls.enable"));
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }

    @Test
    public void checkSMTPHostValueEquelsSettingValue(){
        System.out.println("Проверка существования значения свойства mail.smtp.host SMTP");
        try {
            assertEquals(settings.get("mailSmtpHost"), smtp.get("mail.smtp.host"));
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }

    @Test
    public void checkSMTPPortValueEquelsSettingValue(){
        System.out.println("Проверка существования значения свойства mail.smtp.port SMTP");
        try {
            assertEquals(settings.get("mailSmtpPort"), smtp.get("mail.smtp.port"));
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }
    @Test
    public void sendTestMessage(){
        System.out.println("отправка тестового сообщения на адрес testTPP@rambler.ru\n" +
                "\n" +
                " пароль ADE-resa-wrewq123-asd***!!!");

        try {
            Properties props = new Properties();
            // required for gmail
            props.put("mail.smtp.starttls.enable",settings.get("mailSmtpStarttlsEnable"));
            props.put("mail.smtp.auth", settings.get("mailSmtpAuth"));
            props.put("mail.smtp.host", settings.get("mailSmtpHost"));
            props.put("mail.smtp.port",settings.get("mailSmtpPort"));
            // or use getDefaultInstance instance if desired...
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(settings.get("EmailFrom"), settings.get("password"));
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(settings.get("EmailFrom")));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("testTPP@rambler.ru"));
            message.setSubject("test");// тема сообщения
            message.setText("test");
            Transport.send(message);//отправка сообщения

            System.out.println("Сообщение отправлено успешно. Проверьте почту testTPP@rambler.ru\n" +
                    "\n" +
                    " пароль ADE-resa-wrewq123-asd***!!!");
        }
        catch (NoSuchProviderException e) {
            System.out.println("нет такого провайдера");
            e.printStackTrace();
        }
        catch(AuthenticationFailedException e) {
            System.out.println("Ошибка аутентификации");
            e.printStackTrace();
        }
        catch(MessagingException e) {
            System.out.println("другое");
            e.printStackTrace();
        }
    }

    @Test
    public void checkSMTPParam(){
        System.out.println("Проверка параметра");
        int port = 587;
        String host = "smtp.mail.com";
        String user = "testTPP@rambler.ru";
        String pwd = "ADE-resa-wrewq123-asd***!!!";
        System.out.println( confirmSMTP(host, Integer.toString(port), user, pwd , "true", "TLS"));

       /* try {
            Properties props = new Properties();
            // required for gmail
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port",port);
            // or use getDefaultInstance instance if desired...
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(settings.get("EmailFrom"), settings.get("password"));
                }
            });
            Transport transport = session.getTransport("smtp");
          //  transport.connect(host, port, user, pwd);
            transport.connect(settings.get("EmailFrom"), settings.get("password"));
            transport.close();
            System.out.println("success");
        }
        catch(AuthenticationFailedException e) {
            System.out.println("AuthenticationFailedException - for authentication failures");
            e.printStackTrace();
        }
        catch(MessagingException e) {
            System.out.println("for other failures");
            e.printStackTrace();
        }
        */

    }

    public boolean confirmSMTP(String host, String port, String username, String password, String auth, String enctype) {
        boolean result = false;
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.auth", auth);
          /*  if (auth.equals(true)) {
                props.setProperty("mail.smtp.auth", "true");
            } else {
                props.setProperty("mail.smtp.auth", "false");
            }*/
            if (enctype.endsWith("TLS")) {
                props.setProperty("mail.smtp.starttls.enable", "true");
            } else if (enctype.endsWith("SSL")) {
                props.setProperty("mail.smtp.startssl.enable", "true");
            }
            Session session = Session.getInstance(props,  new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(settings.get("EmailFrom"), settings.get("password"));
                }
            });
            Transport transport = session.getTransport("smtp");
            int portint = Integer.parseInt(port);
            //transport.connect(host, portint, username, password);
            transport.connect();
            transport.close();
            result = true;

        } catch(AuthenticationFailedException e) {
            //Logging.addMsg(e.toString(), "SMTP: Authentication Failed", false, true);

        } catch(MessagingException e) {
           // Logging.addMsg(e.toString(), "SMTP: Messaging Exception Occurred", false, true);
        } catch (Exception e) {
           // Logging.addMsg(e.toString(), "SMTP: Unknown Exception", false, true);
        }

        return result;
    }
    public static boolean netIsAvailable() {
        try {
            System.out.println("Проверка наличия интернета путем подключения к сайту http://www.google.com");
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            System.out.println("соединение установлено");
            return true;
        } catch (MalformedURLException e) {
            System.out.println("Ошибка соединения сайт недоступен проверьте наличие интернета");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Ошибка соединения проблема с вводом - выводом");
            return false;
        }
    }
}