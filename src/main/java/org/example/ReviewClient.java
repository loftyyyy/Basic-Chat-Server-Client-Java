package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ReviewClient {

    private SocketChannel socketChannel;
    private Reader reader;
    private BufferedReader bufferedReader;

    public static void main(String[] args){
        new ReviewClient().start();
    }

    public void start(){

        try {
            socketChannel = SocketChannel.open();
            socketChannel.bind(new InetSocketAddress(5000));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
