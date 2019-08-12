package com.model;
/**
 * @author Землянский Григорий Михайлович
 * @version 1.7
 *
 * класс пользователя
 *
 */
public class User {
    private String name;
    private String email;
    private String phone;
    private String fullName;

    public  User(String name){
        this.name = name.trim();
        this.phone = "" ;
        this.email = "" ;
        this.fullName = "" ;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone.trim();
    }

    @Override
    public String toString() {
        return name;
    }
}
