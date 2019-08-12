package com.model;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Map;

public class SessionEMAIL {
    private static Map<String, String> settings = Settings.getSettings();
    private static Session session = Session.getInstance(PropertiesSMTP.getSMTP(),
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(settings.get("EmailFrom"), settings.get("password"));
                }
            });

    public static Session getSession(){
        return session;
    }
}
