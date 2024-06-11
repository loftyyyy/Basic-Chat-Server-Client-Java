package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.InetSocketAddress;
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
}