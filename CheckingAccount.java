package com.company;

import java.time.LocalDate;

public class CheckingAccount extends Account {

    public CheckingAccount (Client accountHolder, String accountPIN,
                            String internetBankingPassword, long balance) {
        super(accountHolder, accountPIN, internetBankingPassword, balance);
    }

    public CheckingAccount (Client accountHolder, long uniqueIdentifier, String accountPIN, LocalDate dateOfOpeningTheAccount,
                            String internetBankingPassword,int cvv2, long balance) {
        super(accountHolder, uniqueIdentifier, accountPIN, dateOfOpeningTheAccount, internetBankingPassword, cvv2, balance);

    }

    //This method deposits money to an account :
    @Override
    public String deposit (int transferredMoney) {
        setBalance(getBalance() + transferredMoney);

        Transaction transaction = new Transaction("Deposit To", transferredMoney, this);
        this.getTransactions().add(transaction);

        String message = transaction.toString();

        return message;
    }

}
