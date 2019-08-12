package com.test;

import com.control.WinRegistry;
import com.model.Settings;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SettingsTest {
    Map<String, String> settings = Settings.getSettings();
    @Test
    public void checkSettingsNotNull(){
        System.out.println("Проверка существования обьекта settings ");
        try {
            assertNotNull(settings );
            System.out.println("сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }
    @Test
    public void checkSettingsAllValues(){
        System.out.println("Проверка существования свойств обьекта settings ");
        checkSettingsValueEqualsWinRegistre("Agent_repl");
        checkSettingsValueEqualsWinRegistre("dBName");
        checkSettingsValueEqualsWinRegistre("DirLocal");
        checkSettingsValueEqualsWinRegistre("DirServer");
        checkSettingsValueEqualsWinRegistre("Email");
        checkSettingsValueEqualsWinRegistre("EmailFrom");
        checkSettingsValueEqualsWinRegistre("EmailTo");
        checkSettingsValueEqualsWinRegistre("mailSmtpAuth");
        checkSettingsValueEqualsWinRegistre("mailSmtpHost");
        checkSettingsValueEqualsWinRegistre("mailSmtpPort");
        checkSettingsValueEqualsWinRegistre("mailSmtpStarttlsEnable");
        checkSettingsValueEqualsWinRegistre("passwordDB");
        checkSettingsValueEqualsWinRegistre("Phone");
        checkSettingsValueEqualsWinRegistre("port");
        checkSettingsValueEqualsWinRegistre("path");
        checkSettingsValueEqualsWinRegistre("Server");
        checkSettingsValueEqualsWinRegistre("userDataBase");
        checkSettingsValueEqualsWinRegistre("UserName");
    }
    @Test
    public void checkSettingsValueEqualsWinRegistre(String value){
        System.out.println("Проверка соответствия  свойства "+value+" settings  с редактором Реестра");
        String settingsValue = settings.get("value");
        String valueWinRegistre = null;
        try {
            valueWinRegistre = WinRegistry.readString(
                    WinRegistry.HKEY_CURRENT_USER,                             //HKEY
                    "SOFTWARE\\CRAT-SUCCI",           //Key
                    value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            assertEquals(settingsValue,valueWinRegistre);
            System.out.println("соответствует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не соответствует");
            e.printStackTrace();
        }
    }
}