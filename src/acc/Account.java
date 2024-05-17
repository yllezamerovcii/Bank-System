/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acc;

/**
 *
 * @author HP
 */
public class Account {
    private int accountId;
    private String userName;
    private double balance;

    public Account(int accountId, String userName, double balance) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws AccountException {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new AccountException("Please check your funds!");
        }
    }
}
