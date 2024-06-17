package org.example;

public class BankAccount {
    private int balance = 100;

    public int getBalance(){
        return balance;
    }

    public void spend(int amount){
        this.balance = balance - amount;
        if(balance < 0){
            System.out.println("Account overdrawn!");

        }

    }
}
