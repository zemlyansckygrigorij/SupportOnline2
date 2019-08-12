package com.view;

/**
 * @author Землянский Григорий Михайлович
 * @version 1.7
 * класс пользовательского интерфейса пользователя
 *
 * форма сообщения пользователю о возникших ошибках
 *
 * */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameException extends JFrame {
    JButton closeForm  = new JButton("OK");
    JLabel messageAboutError = new JLabel("");
    JLabel messageMainError = new JLabel("<html>Просим обратиться<br> в информационный центр ЮУТПП<br> тел: 266-52-18</html>");
    JPanel panel = new JPanel();
    Container cp = getContentPane();
    public FrameException(   String message){

        this.setTitle("Сообщение об ошибке!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize( 300, 200);
        this.setBackground(new Color(255, 100, 100));

        this.setBounds(300, 200, 400, 300);
        closeForm.setBounds(90, 200, 100, 40);
        messageAboutError.setBounds(10, 10, 5000, 60);
        messageMainError.setBounds(20, 80, 300, 100);

        closeForm.setFont(new Font("Arial", Font.PLAIN, 16));
        messageAboutError.setFont(new Font("Arial", Font.PLAIN, 14));
        messageMainError.setFont(new Font("Arial", Font.PLAIN, 14));
        messageMainError.setFont(new Font("Arial", Font.PLAIN, 14));


        closeForm.addActionListener(new CloseActionListener ());

        panel.setLayout(null);

        panel.add(messageAboutError);
        panel.add(messageMainError);
        panel.add(closeForm);
        messageAboutError.setText(message);
        cp.add( panel);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    private void closeForm(){
        this.setVisible(false);
    }

    public class CloseActionListener  implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            closeForm();
        }
    }
}
