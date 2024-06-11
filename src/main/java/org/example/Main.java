package org.example;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public void createServer(){
        InetSocketAddress serverAddress = new InetSocketAddress("196.164.1.103:", 5000);
        try {
            SocketChannel channel = SocketChannel.open(serverAddress);
            Reader reader = Channels.newReader(channel, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String message = bufferedReader.readLine();


            bufferedReader.close();
            reader.close();
            channel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(){
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1",5000);
        try{
            SocketChannel channel = SocketChannel.open(socketAddress);
            Writer writer = Channels.newWriter(channel, StandardCharsets.UTF_8);
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println("message to send");
            printWriter.print("Another message");

        }catch (Exception e){
            //TODO: Channels vs Sockets
            e.printStackTrace();
        }
    }
}