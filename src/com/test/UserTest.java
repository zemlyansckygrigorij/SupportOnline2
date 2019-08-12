package com.test;

import com.model.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {
    User testUser;
    {
        testUser = new User("test");
    }

    @Test
    public void checkDefaultNameUser(){
        System.out.println("public class UserTest");
        System.out.println("Проверка имени пользователя по умочанию");
        try {
            assertEquals("test", testUser.getName());
            System.out.println("соответствуют ");
        }catch(AssertionError e ){
            System.out.println("Ошибка : не соответствуют ");
            e.printStackTrace();
        }
    }
    @Test
    public void checkDefaultEmailUser(){
        System.out.println("public class UserTest");
        System.out.println("Проверка email по умочанию");
        try {
            assertEquals("", testUser.getEmail());
            System.out.println("соответствуют ");
        }catch(AssertionError e ){
            System.out.println("Ошибка : не соответствуют ");
            e.printStackTrace();
        }
    }
    @Test
    public void checkDefaultPhoneUser(){
        System.out.println("public class UserTest");
        System.out.println("Проверка телефона по умочанию");
        try {
            assertEquals("", testUser.getPhone());
            System.out.println("соответствуют ");
        }catch(AssertionError e ){
            System.out.println("Ошибка : не соответствуют ");
            e.printStackTrace();
        }
    }
    @Test
    public void checkDefaultFullNameUser(){
        System.out.println("public class UserTest");
        System.out.println("Проверка полного имени по умочанию");
        try {
            assertEquals("", testUser.getFullName());
            System.out.println("соответствуют ");
        }catch(AssertionError e ){
            System.out.println("Ошибка : не соответствуют ");
            e.printStackTrace();
        }
    }

    @Test
    public void checkSetNameUser(){
        System.out.println("public class UserTest");
        System.out.println("Проверка имени пользователя ");
        testUser.setName("test123");
        try {
            assertEquals("test123", testUser.getName());
            System.out.println("соответствуют ");
        }catch(AssertionError e ){
            System.out.println("Ошибка : не соответствуют ");
            e.printStackTrace();
        }
    }
    @Test
    public void checkSetEmailUser(){
        System.out.println("public class UserTest");
        System.out.println("Проверка email ");
        testUser.setEmail("test123");
        try {
            assertEquals("test123", testUser.getEmail());
            System.out.println("соответствуют ");
        }catch(AssertionError e ){
            System.out.println("Ошибка : не соответствуют ");
            e.printStackTrace();
        }
    }
    @Test
    public void checkSetPhoneUser(){
        System.out.println("public class UserTest");
        System.out.println("Проверка телефона по умочанию");
        testUser.setPhone("test123");
        try {
            assertEquals("test123", testUser.getPhone());
            System.out.println("соответствуют ");
        }catch(AssertionError e ){
            System.out.println("Ошибка : не соответствуют ");
            e.printStackTrace();
        }
    }
    @Test
    public void checkSetFullNameUser(){
        System.out.println("public class UserTest");
        System.out.println("Проверка полного имени ");
        testUser.setFullName("test123");
        try {
            assertEquals("test123", testUser.getFullName());
            System.out.println("соответствуют ");
        }catch(AssertionError e ){
            System.out.println("Ошибка : не соответствуют ");
            e.printStackTrace();
        }
    }
}