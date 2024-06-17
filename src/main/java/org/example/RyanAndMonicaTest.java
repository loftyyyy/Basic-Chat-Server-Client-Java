package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RyanAndMonicaTest {

    public static void main(String[] args){
        BankAccount bankAccount = new BankAccount();

        RyanAndMonicaJob ryan = new RyanAndMonicaJob("Ryan", bankAccount, 50);
        RyanAndMonicaJob monica = new RyanAndMonicaJob("Monica", bankAccount, 100);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(ryan);
        executorService.execute(monica);
        executorService.shutdown();

    }
}
