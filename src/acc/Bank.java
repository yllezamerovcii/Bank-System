/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acc;

/**
 *
 * @author HP
 */
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bankName;
    private List<Account> accounts;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFeeAmount;
    private double transactionPercentFee;

    public Bank(String bankName, double transactionFlatFeeAmount, double transactionPercentFee) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
        this.transactionPercentFee = transactionPercentFee;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void performTransaction(Account originAccount, Account resultAccount, double amount, String reason)
            throws AccountException {
        double totalAmount = calculateTotalAmount(amount);
        originAccount.withdraw(totalAmount);
        resultAccount.deposit(amount);
        totalTransactionFeeAmount += calculateTransactionFee(amount);
        totalTransferAmount += amount;
        Transaction transaction = new Transaction(amount, originAccount.getAccountId(),
                resultAccount.getAccountId(), reason);
        // Add transaction to account history or log
    }

    private double calculateTotalAmount(double amount) {
        return amount + transactionFlatFeeAmount + (amount * transactionPercentFee / 100);
    }

    private double calculateTransactionFee(double amount) {
        return transactionFlatFeeAmount + (amount * transactionPercentFee / 100);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public double getTransactionFlatFeeAmount() {
        return transactionFlatFeeAmount;
    }

    public double getTransactionPercentFee() {
        return transactionPercentFee;
    }
}
