/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acc;

/**
 *
 * @author HP
 */
public class Transaction {
    private double amount;
    private int originAccountId;
    private int resultAccountId;
    private String reason;

    public Transaction(double amount, int originAccountId, int resultAccountId, String reason) {
        this.amount = amount;
        this.originAccountId = originAccountId;
        this.resultAccountId = resultAccountId;
        this.reason = reason;
    }
}
