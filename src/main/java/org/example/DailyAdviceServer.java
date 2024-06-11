package org.example;

import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class DailyAdviceServer {
    final private String[] adviceList = {
            "Take smaller bites",
            "Go for the tight jeans. No they do NOT make you look fat.",
            "One word: inappropriate",
            "Just for today, be honest. Tell your boss what you *really* think",
            "You might want to rethink that haircut."};
    public static void main(String[] args){

        new DailyAdviceServer().startServer();
    }

    public void startServer(){

        try(ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()){
            serverSocketChannel.bind(new InetSocketAddress(5000));

            while(serverSocketChannel.isOpen()){
                SocketChannel socketChannel = serverSocketChannel.accept();
                PrintWriter writer = new PrintWriter(Channels.newOutputStream(socketChannel));

                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getAdvice(){
        Random random = new Random();
        String advice = adviceList[random.nextInt(adviceList.length)];
        return advice;
    }
}
