package org.example;

import org.w3c.dom.ls.LSOutput;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChatClient {
    private JTextField messageField;
    private PrintWriter writer;
    private SocketChannel channel;
    private Reader reader;
    private JTextArea textArea;


    public static void main(String[] args){
        new SimpleChatClient().go();

    }

    public void go(){
        setUpNetworking();
        System.out.println("go() class called");
        JFrame frame = new JFrame("Simple Chat Client");
        JPanel panel = new JPanel();
        JButton button = new JButton("Send");
        messageField = new JTextField(32);
        JLabel label = new JLabel("Message");

        JScrollPane chatArea = createScrollableTextArea();

        button.addActionListener(event -> sendMessage());
        panel.add(chatArea);
        panel.add(label);
        panel.add(messageField);
        panel.add(button);


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new getMessage());

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setSize(400,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                closeConnection();
            }
        });

    }
    private JScrollPane createScrollableTextArea(){
        textArea = new JTextArea(15,30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane scroller = new JScrollPane(textArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        return scroller;


    }

    public void setUpNetworking(){

        try{
            channel = SocketChannel.open(new InetSocketAddress(5000));
            writer = new PrintWriter(Channels.newWriter(channel, StandardCharsets.UTF_8));
            reader = Channels.newReader(channel, StandardCharsets.UTF_8);
            System.out.println("Connection Started");

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public class getMessage implements Runnable {
        @Override
        public void run() {
            BufferedReader bufferedReader = new BufferedReader(reader);
            System.out.println("get message called");
            try{
                String message;
                while((message = bufferedReader.readLine()) != null){
                    textArea.append(message + "\n");
                }

            }catch (Exception e){
                System.out.println("Client Closed");
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

        }



    }
    public void sendMessage(){
        if(!messageField.getText().isBlank()){
//            textArea.append(messageField.getText() + "\n");
            writer.println(messageField.getText());
            writer.flush();
            messageField.setText("");
            messageField.requestFocus();


        }else{
            System.out.println("Must not be emt!");
            messageField.requestFocus();


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

