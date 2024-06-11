package org.example;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;

public class DailyAdviceClient {

    public static void main(String[] args){
        InetSocketAddress serverAddress = new InetSocketAddress( 5000);
        try(SocketChannel channel = SocketChannel.open(serverAddress)){
            Reader reader = Channels.newReader(channel, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String advice = bufferedReader.readLine();
            System.out.println("Today, " + advice);

            reader.close();
            bufferedReader.close();


        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
