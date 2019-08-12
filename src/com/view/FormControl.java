package com.view;

/**
 * @author Землянский Григорий Михайлович
 * @version 1.7
 * класс пользовательского интерфейса пользователя
 *
 *основная форма
 *
 * */

import javax.swing.*;
import com.model.*;
import com.control.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class FormControl extends JFrame {




    Set<String> errors = new ListError().getListError();

    JButton sendClaim  = new JButton("Отправить заявку");
    JButton getSupport  = new JButton("Ответить тех.поддержке");
    JList listError = new JList(errors.toArray());
    JLabel selectError = new JLabel("Выберите ошибку");
    JLabel labelAdditionalInformation = new JLabel("Введите дополнительную информацию");
    JLabel warning = new JLabel("<html>эту кнопку нажимать только после <br> звонка из тех.поддержки <html>" );
    JLabel labelUserName = new JLabel("Пользователь" );
    JLabel messageForUser = new JLabel("");
    JTextField userName = new TextFieldUserName();
    JTextArea additionalInformation = new JTextArea();
    JPanel panel = new JPanel();
    Container cp = getContentPane();
    JMenu helpMenu = new  JMenu("Помощь");
    JMenuBar menuBar = new JMenuBar();
    JMenuItem adminFileItem = new JMenuItem("для администраторов");
    JMenuItem userFileItem = new JMenuItem("для пользователей");
    JMenuItem runTests = new JMenuItem("run tests ");
    final JScrollPane scrollPane = new JScrollPane(listError);


    public FormControl(){
        helpMenu.add(adminFileItem);
        helpMenu.add(userFileItem);
        helpMenu.add(runTests);

        this.setTitle("Программа автоматизации заявок");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize( 700, 560);
        this.setBackground(new Color(255, 100, 100));
        this.setBounds(200, 100, 700, 560);

        // свойство переноса строк поле дополнительная информация
        additionalInformation.setLineWrap(true);
        additionalInformation.setWrapStyleWord(true);

        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);
        // прикрепление к кнопкам обработчиков событий
        userFileItem.addActionListener(new  HelpMenuUserOnlineActionListener());
        adminFileItem.addActionListener(new  HelpMenuAdminOnlineActionListener());
        ActionListener actionListenerSendClaim = new SendClaimActionListener();
        sendClaim.addActionListener(actionListenerSendClaim);

        ActionListener actionListenerSupportOnline = new SupportOnlineActionListener();
        getSupport.addActionListener(actionListenerSupportOnline);

        runTests.addActionListener(new RunTestsActionListener());
        listError.setAutoscrolls(true);
        listError.setSelectedIndex(0);
        // обработка поля ввода дополнительной информации
        additionalInformation.setAutoscrolls(true);

        setFontComponents();
        setPanelSettings();
        setBoundsComponent();


        cp.add( panel);

        this.setVisible(true);
    }
    private void setBoundsComponent(){
        selectError.setBounds(10, 10, 200, 20);
        listError.setBounds(10, 30, 300, 300);
        scrollPane.setBounds(10, 30, 300, 300);
        labelAdditionalInformation.setBounds(320, 0, 300, 20);
        additionalInformation.setBounds(320, 30, 300, 300);
        labelUserName.setBounds(10, 330, 300, 20);
        userName.setBounds(10, 350, 300, 20);
        sendClaim.setBounds(320, 345, 300, 30);
        getSupport.setBounds(320, 420, 300, 30);
        warning.setBounds(10, 420, 300, 40);
        messageForUser.setBounds(10, 395, 300, 30);
    }
    private void setFontComponents(){
        selectError.setFont(new Font("Arial", Font.PLAIN, 16));
        labelAdditionalInformation.setFont(new Font("Arial", Font.PLAIN, 16));
        labelUserName.setFont(new Font("Arial", Font.PLAIN, 16));
        sendClaim.setFont(new Font("Arial", Font.PLAIN, 16));
        getSupport.setFont(new Font("Arial", Font.PLAIN, 16));
        warning.setFont(new Font("Arial", Font.PLAIN, 16));
        listError.setFont(new Font("Arial", Font.PLAIN, 14));
        messageForUser.setFont(new Font("Arial", Font.PLAIN, 18));
    }
    private void setPanelSettings(){
        panel.setLayout(null);
        // panel.add( helpMenu);

        panel.add( scrollPane);
        panel.add(sendClaim);
        panel.add(getSupport);
        panel.add(selectError);
        panel.add(labelAdditionalInformation);
        panel.add(warning);
        panel.add(userName);
        panel.add(additionalInformation);
        panel.add(labelUserName);
        panel.add(messageForUser);
    }

    public class SendClaimActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            // проверка выбрана ли ошибка из списка
            if(listError.isSelectionEmpty()){
                messageForUser.setText("Выберите ошибку !");
                return;
            }
            // проверка выбран ли пользователь
            if(userName.getText().isEmpty()){
                messageForUser.setText("Вставьте пользователя !");
                return;
            }
            if(additionalInformation.getText().isEmpty()){
                additionalInformation.setText("");
            }else{
                additionalInformation.setText("-"+additionalInformation.getText());
            }

            // отправка сообщения
            ControlMessage.sendMessage(userName.getText(),listError.getSelectedValue().toString()+additionalInformation.getText().toString());
            additionalInformation.setText("");
        }
    }

    public class SupportOnlineActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ControlTeamViewer.runTeamviewer();

        }
    }
    public class HelpMenuUserOnlineActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ControlMenu.getHtml("helpUser.html");
        }
    }
    public class HelpMenuAdminOnlineActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ControlMenu.getHtml("helpAdmin.html");
        }
    }
    public class RunTestsActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ControlMenu.runTest();
        }
    }
}

