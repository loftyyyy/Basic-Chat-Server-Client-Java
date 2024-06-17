package org.example;

public class BankAccount {
    private int balance = 100;

    public int getBalance(){
        return balance;
    }

    public void spend(int amount){
        this.balance = balance - amount;
        if(amount > balance){
            System.out.println("Account overdrawn!");

        }

    }
}
