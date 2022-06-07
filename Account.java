package com.company;

import java.time.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Account {

    private Client accountHolder;
    private final LocalDate dateOfOpeningTheAccount;
    private final long uniqueIdentifier;
    private static long identifierMaker = 6362141010101010L;
    private String accountPIN;
    private String internetBankingPassword;
    private int cvv2;
    private static int cvv2Maker = 100;
    private long balance;
    private LocalDate dateOfTransferringPeriodically;
    protected final static int transferWithdraw = 5000;
    public final static int leastMoney = 100000;
    private ArrayList <Transaction> transactions = new ArrayList<>();

    public Account (Client accountHolder, String accountPIN,
                    String internetBankingPassword, long balance) {
        this.accountHolder = accountHolder;
        this.dateOfOpeningTheAccount = DateAndTime.getToday();
        this.accountPIN = accountPIN;
        this.internetBankingPassword = internetBankingPassword;
        this.balance = balance;

        for (int i = 0; i < Accessibility.getClients().size(); i++) {
            for (int j = 0; j < Accessibility.getClients().get(i).getAccounts().size(); j++) {
                identifierMaker += (Accessibility.getClients().get(i)
                        .getAccounts().get(j).getUniqueIdentifier() % 1000000);

                cvv2Maker += (Accessibility.getClients().get(i)
                        .getAccounts().get(j).getCvv2() % 10);
            }
        }
        
        this.cvv2 = cvv2Maker;
        this.uniqueIdentifier = identifierMaker;

        identifierMaker ++;
        cvv2Maker ++;
    }
    public Account (Client accountHolder, long uniqueIdentifier, String accountPIN, LocalDate dateOfOpeningTheAccount,
                    String internetBankingPassword,int cvv2, long balance) {
        this.accountHolder = accountHolder;
        this.dateOfOpeningTheAccount = dateOfOpeningTheAccount;
        this.uniqueIdentifier = uniqueIdentifier;
        this.accountPIN = accountPIN;
        this.internetBankingPassword = internetBankingPassword;
        this.cvv2 =cvv2 ;
        this.balance = balance;
    }


    public int getCvv2() {
        return cvv2;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public Client getAccountHolder() {
        return accountHolder;
    }

    public static int getLeastMoney() {
        return leastMoney;
    }

    public LocalDate getDateOfOpeningTheAccount() {
        return dateOfOpeningTheAccount;
    }

    public ArrayList<Transaction> getTransactions () {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public static int getTransferWithdraw() {
        return transferWithdraw;
    }

    public void setAccountPIN(String accountPIN) {
        this.accountPIN = accountPIN;
    }

    public String getInternetBankingPassword() {
        return internetBankingPassword;
    }

    public String getAccountPIN() {
        return accountPIN;
    }

    public void setDateOfTransferringPeriodically(LocalDate dateOfTransferringPeriodically) {
        this.dateOfTransferringPeriodically = dateOfTransferringPeriodically;
    }

    public LocalDate getDateOfTransferringPeriodically() {
        return dateOfTransferringPeriodically;
    }

    public abstract String deposit (int transferredMoney);

    //This method is called for transferring, it actually checks if transfer is possible or not :
    public void withdraw(int transferredMoney, Account account1, Account account2) {
        String result = "";

        if (account2 instanceof LongTermSavingAccount) {
            result = "You Can Not Transfer Money To A Long Term Saving Account !";
            MessageScene.message(result, Accessibility.client);
        }
        else {
            AccountScene.doTransfer(transferredMoney, account1, account2);
        }
    }

    //This method is kind of getter for balance :
    public String accountsBalance () {
        return String.format("There Is %d Rials In This Account.", getBalance());
    }

    @Override
    public String toString () {
        return String.format("\nAccount Holder : %s %s, \nUnique Identifier : %d , \nBalance : %d, \ncvv2 : %d\n",
                              this.accountHolder.getFirstName(), this.accountHolder.getFamilyName(),
                              this.getUniqueIdentifier(), this.getBalance(), this.getCvv2());
    }
}
