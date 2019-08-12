package com.test;

import com.model.*;
import org.junit.Test;

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
}