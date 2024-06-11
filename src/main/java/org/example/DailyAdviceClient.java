package org.example;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.Scanner;

public class DailyAdviceClient {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        InetSocketAddress serverAddress = new InetSocketAddress( 5000);
        try(SocketChannel channel = SocketChannel.open(serverAddress)){
//            Reader reader = Channels.newReader(channel, StandardCharsets.UTF_8);
//            BufferedReader bufferedReader = new BufferedReader(reader);
//
//            String advice = bufferedReader.readLine();
//            System.out.println("Today, " + advice);
//
//            reader.close();
//            bufferedReader.close();

            boolean continues = true;

            while(continues){
                PrintWriter writer = new PrintWriter(Channels.newOutputStream(channel));
                System.out.print("What's your message? ");
                String message = scanner.nextLine();
                writer.println(message);
                if(message.equals("x")){
                    continues = false;
                }
                writer.flush();
            }


        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
