package com.model;

/**
 * @author Землянский Григорий Михайлович
 * @version 1.7
 * класс для установки свойств smtp протокола
 * данные беруться из редактора реестра Windows
 *
 * */
import java.util.Map;
import java.util.Properties;

public class PropertiesSMTP {
    private static Properties smtp = new Properties();
    private static Map<String, String> settings = Settings.getSettings();
    static{
        smtp.put("mail.smtp.auth",settings.get("mailSmtpAuth"));
        smtp.put("mail.smtp.starttls.enable", settings.get("mailSmtpStarttlsEnable"));
        smtp.put("mail.smtp.host", settings.get("mailSmtpHost"));
        smtp.put("mail.smtp.port",settings.get("mailSmtpPort"));
    }
    public static Properties getSMTP(){
        return smtp;
    }
}
