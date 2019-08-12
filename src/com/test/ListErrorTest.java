package com.test;

import com.model.*;
import org.junit.Test;
import java.util.Set;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ListErrorTest {
    private Set<String> errors = new ListError().getListError();

    @Test
    public void checkListErrorNotNull(){
        System.out.println("Проверка существования списка ошибок");
        try {
            assertNotNull(errors);
            System.out.println("список ошибок сушествует");
        }catch(AssertionError e ){
            System.out.println("Ошибка : списка ошибок нет");
            e.printStackTrace();
        }
    }
    @Test
    public void checkSizeListError(){
        System.out.println("Проверка количества значений в  списке ошибок");
        try {
            assertTrue(errors.size()>0);
            System.out.println("в списке ошибок есть значения ");
        }catch(AssertionError e ){
            System.out.println("Ошибка : в списке ошибок нет значений ");
            e.printStackTrace();
        }
    }

    @Test
    public void checkStringListErrorExist(){
        System.out.println("Проверка значений в списке ошибок");
        StringBuilder sb = new StringBuilder("");
//проверить если все значения в списке пустые
        for (String text : errors)
        {
            sb.append(text.trim());
        }
        try {
            assertTrue(sb.length()>0);
            System.out.println("в списке ошибок есть значения ");
        }catch(AssertionError e ){
            System.out.println("Ошибка : в списке ошибок нет значений ");
            e.printStackTrace();
        }
    }
}