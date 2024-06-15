package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ReviewClient {
    private Scanner input = new Scanner(System.in);

    private SocketChannel socketChannel;
    private Reader reader;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public static void main(String[] args){
        new ReviewClient().start();
    }

    public void start(){

        try {
            socketChannel = SocketChannel.open(new InetSocketAddress(5000));
//            socketChannel.bind(new InetSocketAddress(5000));
            printWriter = new PrintWriter(Channels.newWriter(socketChannel, StandardCharsets.UTF_8));
            sendMessage();





        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void sendMessage(){


        boolean continues = true;

        String message;
        while(continues){

            System.out.print("Message: ");
            message = input.nextLine();
            if(message.equals("n")){
                continues = false;

            }else{
                printWriter.println(message);
                printWriter.flush();
            }


        }
    }
}
