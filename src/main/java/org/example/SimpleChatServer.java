package org.example;

import java.io.BufferedReader;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class SimpleChatServer {

    public static void main(String[] args){
        new SimpleChatServer().startServer();

    }

    public void startServer(){
        try(ServerSocketChannel channel = ServerSocketChannel.open()){
            channel.bind(new InetSocketAddress(5000));

            while(channel.isOpen()){
                SocketChannel socketChannel = channel.accept();
                Reader reader = Channels.newReader(socketChannel, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String message;
                while((message = bufferedReader.readLine()) != null){
                    System.out.println(message);
                }
                bufferedReader.close();
                reader.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
