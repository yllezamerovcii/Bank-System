/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package acc;

/**
 *
 * @author HP
 */
import java.util.Scanner;

public class BankSystemMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create Bank
        Bank bank = createBank(scanner);

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Perform Transaction");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. View Transactions");
            System.out.println("6. Check Account Balance");
            System.out.println("7. View Bank Accounts");
            System.out.println("8. Check Total Transaction Fee Amount");
            System.out.println("9. Check Total Transfer Amount");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount(scanner, bank);
                    break;
                case 2:
                    performTransaction(scanner, bank);
                    break;
                case 3:
                    depositMoney(scanner, bank);
                    break;
                case 4:
                    withdrawMoney(scanner, bank);
                    break;
                case 5:
                    viewTransactions(scanner, bank);
                    break;
                case 6:
                    checkAccountBalance(scanner, bank);
                    break;
                case 7:
                    viewBankAccounts(bank);
                    break;
                case 8:
                    checkTotalTransactionFeeAmount(bank);
                    break;
                case 9:
                    checkTotalTransferAmount(bank);
                    break;
                case 10:
                    System.out.println("Exiting the Bank System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Bank createBank(Scanner scanner) {
        System.out.print("Enter Bank Name: ");
        String bankName = scanner.nextLine();
        System.out.print("Enter Transaction Flat Fee Amount: $");
        double transactionFlatFeeAmount = scanner.nextDouble();
        System.out.print("Enter Transaction Percent Fee: ");
        double transactionPercentFee = scanner.nextDouble();

        return new Bank(bankName, transactionFlatFeeAmount, transactionPercentFee);
    }

    private static void createAccount(Scanner scanner, Bank bank) {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Initial Balance: $");
        double initialBalance = scanner.nextDouble();

        Account account = new Account(accountId, userName, initialBalance);
        bank.addAccount(account);
        System.out.println("Account created successfully.");
    }

    private static void performTransaction(Scanner scanner, Bank bank) {
        System.out.print("Enter Origin Account ID: ");
        int originAccountId = scanner.nextInt();
        System.out.print("Enter Result Account ID: ");
        int resultAccountId = scanner.nextInt();
        System.out.print("Enter Transaction Amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Transaction Reason: ");
        String reason = scanner.nextLine();

        Account originAccount = findAccountById(bank, originAccountId);
        Account resultAccount = findAccountById(bank, resultAccountId);

        if (originAccount != null && resultAccount != null) {
            try {
                bank.performTransaction(originAccount, resultAccount, amount, reason);
                System.out.println("Transaction successful.");
            } catch (AccountException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    private static void depositMoney(Scanner scanner, Bank bank) {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter Deposit Amount: $");
        double amount = scanner.nextDouble();

        Account account = findAccountById(bank, accountId);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawMoney(Scanner scanner, Bank bank) {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter Withdrawal Amount: $");
        double amount = scanner.nextDouble();

        Account account = findAccountById(bank, accountId);
        if (account != null) {
            try {
                account.withdraw(amount);
                System.out.println("Withdrawal successful.");
            } catch (AccountException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewTransactions(Scanner scanner, Bank bank) {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Account account = findAccountById(bank, accountId);
        if (account != null) {
            // Implement logic to view transactions for the account
            System.out.println("Transaction history for Account ID " + account.getAccountId() + ":");
            // Placeholder for displaying transactions
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkAccountBalance(Scanner scanner, Bank bank) {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = findAccountById(bank, accountId);
        if (account != null) {
            System.out.println("Account Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewBankAccounts(Bank bank) {
        System.out.println("List of Bank Accounts:");
        for (Account account : bank.getAccounts()) {
            System.out.println("Account ID: " + account.getAccountId() + " | User Name: " + account.getUserName()
                    + " | Balance: $" + account.getBalance());
        }
    }

    private static void checkTotalTransactionFeeAmount(Bank bank) {
        System.out.println("Total Transaction Fee Amount: $" + bank.getTotalTransactionFeeAmount());
    }
    
    private static void checkTotalTransferAmount(Bank bank) {
        System.out.println("Total Transfer Amount: $" + bank.getTotalTransferAmount());
        
    }

    private static Account findAccountById(Bank bank, int accountId) {
        for (Account account : bank.getAccounts()) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null; // Account not found
    }
}
