package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChatServer {

    private final ArrayList<PrintWriter> clientWriter = new ArrayList<>();

    public static void main(String[] args){
        new SimpleChatServer().startServer();

    }

    public void startServer(){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try{
        ServerSocketChannel channel = ServerSocketChannel.open();
            channel.bind(new InetSocketAddress(5000));

            while(channel.isOpen()){
                SocketChannel socketChannel = channel.accept();
                PrintWriter writer = new PrintWriter(Channels.newWriter(socketChannel, StandardCharsets.UTF_8),true);
                clientWriter.add(writer);
                threadPool.submit(new ClientHandle(socketChannel));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public class ClientHandle implements Runnable{
        SocketChannel socket;
        BufferedReader reader;
        public ClientHandle(SocketChannel socketChannel) {
            this.socket = socketChannel;
            this.reader = new BufferedReader(Channels.newReader(socket, StandardCharsets.UTF_8));

        }
        public void tellEveryone(String message){
            for(PrintWriter writer : clientWriter){
                writer.println(message);
            }

        }



        @Override
        public void run() {

            String message;
            try{
                while((message = reader.readLine()) != null){
                    System.out.println("read " + message);
                    tellEveryone(message);

                }

            }catch (Exception e){
                System.out.println("Closed unexpectedly");
            }finally {
                try{
                    System.out.println("Client has exited");
                    socket.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }


        }
    }
}
