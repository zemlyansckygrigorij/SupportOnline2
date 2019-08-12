package com.control;

/**
 * @author Землянский Григорий Михайлович
 * @version 1.7
 *
 * класс для получения текущих даты и времени в  заданном шаблоне.
 * При первой загрузке класса формируется формат даты времени.
 * При вызове функции getDateTime() получает текущую дату время
 *
 */
import com.view.*;
import com.model.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ControlDateTime {
    private String dateTime;
    private SimpleDateFormat formatter;

    public ControlDateTime(String pattern){
        this.formatter = new SimpleDateFormat(pattern);
    }

    public  String getDateTime(){
        Calendar now = Calendar.getInstance();
        dateTime = formatter.format( now.getTime());
        return dateTime;
    }

}
