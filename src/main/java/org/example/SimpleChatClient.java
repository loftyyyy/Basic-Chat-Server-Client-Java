package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class SimpleChatClient {
    private JTextField messageField;
    private PrintWriter writer;
    private SocketChannel channel;


    public static void main(String[] args){
        new SimpleChatClient().go();

    }

    public void go(){
        setUpNetworking();
        JFrame frame = new JFrame("Simple Chat Client");
        JPanel panel = new JPanel();
        JButton button = new JButton("Send");
        messageField = new JTextField(32);
        JLabel label = new JLabel("Message");

        button.addActionListener(event -> sendMessage());
        panel.add(label);
        panel.add(messageField);
        panel.add(button);



        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setSize(400,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                closeConnection();
            }
        });

    }
    public void setUpNetworking(){

        try{
            channel = SocketChannel.open(new InetSocketAddress(5000));
            writer = new PrintWriter(Channels.newWriter(channel, StandardCharsets.UTF_8));
            System.out.println("Connection Started");

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void sendMessage(){
        if(!messageField.getText().isBlank()){
            writer.println(messageField.getText());
            writer.flush();
            messageField.setText("");
            messageField.requestFocus();

            closeConnection();
            setUpNetworking();
        }else{
            System.out.println("Must not be emt!");
        }
    }
    public void closeConnection(){
        System.out.println("Connection Closed");
        try{
            if(writer != null){
                writer.close();
            }
            if(channel != null){
                channel.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
