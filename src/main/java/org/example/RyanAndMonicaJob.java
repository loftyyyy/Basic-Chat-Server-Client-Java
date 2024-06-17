package org.example;

public class RyanAndMonicaJob implements Runnable{
    private final String name;
    private final BankAccount account;
    private final int amountToSpend;

    RyanAndMonicaJob(String name, BankAccount account, int amountToSpend){
        this.name = name;
        this.account = account;
        this.amountToSpend = amountToSpend;

    }



    @Override
    public void run() {
        goShopping(amountToSpend);


    }
    public void goShopping(int amount){
        synchronized (account){
            if(account.getBalance() >= amount){
                System.out.println(name + " is about to spend " + amount);
                account.spend(amount);
                System.out.println(name + " finished paying "+ account.getBalance());
            }else{
                System.out.println("Sorry, there's not enough balance " + name);

            }

        }
    }
}
