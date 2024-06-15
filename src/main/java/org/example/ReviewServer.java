package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ReviewServer {
    private ArrayList<PrintWriter> printWriters = new ArrayList<>();
    private SocketChannel socketChannel;
    private PrintWriter printWriter;


    public static void main(String[] args){
        new ReviewServer().start();
    }
    public void start(){
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(5000));

            while(serverSocketChannel.isOpen()){
                socketChannel = serverSocketChannel.accept();
                printWriter = new PrintWriter(Channels.newWriter(socketChannel, StandardCharsets.UTF_8));
                printWriters.add(printWriter);
                readMessage();


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void readMessage(){

    }

    public void sendMessage(String message){

        for(PrintWriter writer : printWriters){
            writer.println(message);
            writer.flush();

        }


    }

}
