package com.model;

/**
 * @author Землянский Григорий Михайлович
 * @version 1.7
 *
 * класс для формирования обьекта сообщения.
 * При загрузке класса формируется сессия интернет связи по smtp протоколу .
 * формируется тело сообщения состоящее из текста и файла
 * при вызове конструктора MessageEMAIL(String text, File file)
 *
 * или только из текста при вызове конструктора MessageEMAIL(String text ) .
 *
 * сообщение отправляется на адрес указанный
 * в переменной редактора  реестра EmailTo
 *  в случае удачной отправке пользователю выдается сообщение об удачной отправке
 *  и производиться запись в файл ListClaim.txt
 *  в случае ошибки выдается сообщение об ошибке и создается  в файл ошибки
 */
import com.view.*;
import com.control.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.swing.*;
import java.io.File;
import java.util.Map;

public class MessageEMAIL extends MimeMessage {
    private  Map<String, String> settings = Settings.getSettings();

    public MessageEMAIL(String text, File file) {
        super(SessionEMAIL.getSession());//создаем сессию smtp
        MimeBodyPart messageBodyPart = new MimeBodyPart(); //формируем тело сообщения
        Multipart multipart = new MimeMultipart();

        DataSource source = new FileDataSource(file);// формируем файл для отправки
        MimeBodyPart textBodyPart = new MimeBodyPart();
        try {
            textBodyPart.setText(text);// текст сообщения
            messageBodyPart.setDataHandler(new DataHandler(source));// подключаем файл
            messageBodyPart.setFileName(file.getName());//имя подключенного файла
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(textBodyPart);  // add the text part
            this.setContent(multipart);


            this.setFrom(new InternetAddress(settings.get("EmailFrom")));
            this.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(settings.get("EmailTo")));
            this.setSubject("Палата торгово-промышленная ЮУ");// тема сообщения

            Transport.send(this);//отправка сообщения
            JFrame myWindow = new FrameMessage(" Сообщение отправлено!!!");
        } catch (MessagingException e) {

            JFrame myWindow = new FrameException(" Отсутствует файл  отправки сообщения!!!");
            Settings.writeError(e," Отсутствует файл  отправки сообщения " +file.toPath());// создать файл ошибки
            e.printStackTrace();
            return;
        }

    }

    public MessageEMAIL(String text ) {
        super(SessionEMAIL.getSession());//создаем сессию smtp

        try {

            this.setFrom(new InternetAddress(settings.get("EmailFrom")));
            this.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(settings.get("EmailTo")));
            this.setSubject("Палата торгово-промышленная ЮУ");// тема сообщения
            this.setText(text);// текст сообщения
            Transport.send(this);//отправка сообщения
            JFrame myWindow = new FrameMessage(" Сообщение отправлено!!!");

            // запись сообщения в файл заявок

            Settings.writeClaim(new ControlDateTime("dd.MM.yyyy hh-mm").getDateTime() +"\n"+text+"\n\n\n\n");

        } catch (MessagingException e) {
            JFrame myWindow = new FrameException(" Ошибка отправки сообщения !!!");
            Settings.writeError(e, " Ошибка отправки сообщения !!!");// создать файл ошибки
            e.printStackTrace();
            return;
        }

    }
}
