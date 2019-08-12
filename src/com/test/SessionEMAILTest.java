package com.test;

import com.model.SessionEMAIL;
import org.junit.Test;

import javax.mail.Session;

import static org.junit.Assert.assertNotNull;

public class SessionEMAILTest {
    Session session = SessionEMAIL.getSession();
    @Test
    public void checkSessionEMAILNotNull(){
        System.out.println("Проверка существования сеанса связи ");
        try {
            assertNotNull(session);
            System.out.println(" сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }

    @Test
    public void checkSessionEMAIL(){
        System.out.println("Проверка существования сеанса связи ");

        try {
            assertNotNull(session);
            System.out.println(" сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка :  не сушествует");
            e.printStackTrace();
        }
    }
}
