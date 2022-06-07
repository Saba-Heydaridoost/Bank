package com.company;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class Transaction {

    private long evidenceCode;
    private static long evidenceCodeMaker = 10000L;
    private LocalDate date;
    private ZonedDateTime time;
    private String type;
    private int amount;
    private Account account;

    public Transaction (String type, int amount, Account account) {
        this.date = DateAndTime.getToday();
        this.time = DateAndTime.getTime();
        this.type = type;
        this.amount = amount;
        this.account = account;

        for (int i = 0; i < Accessibility.getClients().size(); i++) {
            for (int j = 0; j < Accessibility.getClients().get(i).getAccounts().size(); j++) {
                for (int k = 0; k < Accessibility.getClients().get(i).getAccounts().get(j).getTransactions().size(); k++) {
                    evidenceCodeMaker += (Accessibility.getClients().get(i).getAccounts().get(j)
                            .getTransactions().get(k).evidenceCode % 10) ;
                }
            }
        }

        this.evidenceCode = evidenceCodeMaker;
        evidenceCodeMaker += 7;
    }
    public Transaction ( Account account, long evidenceCode, LocalDate date, ZonedDateTime time,
                         String type,int amount) {
        this.account = account;
        this.evidenceCode = evidenceCode;
        this.date = date;
        this.time = time;
        this.type = type;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getEvidenceCode() {
        return evidenceCode;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        String message = "";
        long balance;
        if (this.account instanceof ShortTermSavingAccount) {
            ShortTermSavingAccount shortTermSavingAccount = (ShortTermSavingAccount) account;
            balance = account.getBalance() + shortTermSavingAccount.interest(shortTermSavingAccount);
        }
        else if (this.account instanceof LongTermSavingAccount) {
            LongTermSavingAccount longTermSavingAccount = (LongTermSavingAccount) account;
            balance = account.getBalance() + longTermSavingAccount.interest(longTermSavingAccount);
        }
        else {
            balance = account.getBalance();
        }

        message += this.getTime() +
                String.format(" \n%s : %d \nAmount : %d Rials \nBalance : %d Rials \nEvidence Code : %d",
                        this.getType(), account.getUniqueIdentifier(), amount,
                        balance, this.getEvidenceCode());

        return message;
    }
}
