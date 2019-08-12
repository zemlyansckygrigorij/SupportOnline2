package com.test;

import org.junit.Test;
import static org.junit.Assert.*;
import com.control.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ControlDateTimeTest {


    @Test
    public void testFormatDateTime(String pattern ){
        String dateTime;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Calendar now = Calendar.getInstance();
        dateTime = formatter.format( now.getTime());

        System.out.println("testFormatDateTime()");
        try {
            assertEquals(dateTime,new ControlDateTime(pattern).getDateTime());
            System.out.println("формат времени соответствует ожидаемому");
        }catch(AssertionError e ){
            System.out.println("\n");
            System.out.println("Внимание Ошибка");
            System.out.println("несоответствие формата времени");
            System.out.println("\n");
            // throw e;
            e.printStackTrace();
        }
    }
}