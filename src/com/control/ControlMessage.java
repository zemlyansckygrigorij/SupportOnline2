package com.control;

/**
 * @author Землянский Григорий Михайлович
 * @version 1.7
 *
 * класс для формирования и отправки сообщения.
 * При первой загрузке класса формируется пользователь по умолчанию .
 *
 *  при вызове функции sendMessage(String userName,String text)
 * происходит проверка наличия пользователя в базе данных по имени пользователя
 * если пользователь отсутствует ,
 * то происходит отправка пользователя по умолчанию.
 */
import com.model.*;
import com.view.*;


public class ControlMessage {
    private static User defaultUser ;

    static{
        defaultUser = new User(Settings.getSettings().get("UserName"));
        defaultUser.setFullName(Settings.getSettings().get("UserName"));
        defaultUser.setPhone(Settings.getSettings().get("Phone"));
        defaultUser.setEmail(Settings.getSettings().get("Email"));
    }

    public static void sendMessage(String userName,String text){
        User user = defaultUser;

        try{
            user  = UserRepository.getUser(userName);
            if(user==null)  user  = UserRepository.getUserByLastName(userName);
        }catch(Exception e){
            Settings.writeError(e, "Ошибка возникла при подключении к базе данных");
            e.printStackTrace();
        }finally{
            if(user==null) user = defaultUser;

            String message = "ФИО:"+user.getFullName()+"\n"+
                    "Тел:"+user.getPhone()+"\n"+
                    "IP:"+Settings.getIP() +"\n" +
                    "e-mail:"+user.getEmail()+"\n"+
                    "TeamViewer/AnyDesk:"+"\n\n"+ text;

            new MessageEMAIL(message);// отправка сообщения
        }
    }
}
