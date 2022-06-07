package com.company;

import java.time.LocalDate;
import java.util.ArrayList;

public class ShortTermSavingAccount extends Account {

    private final static double shortTermInterestRate = 0.08;
    private ArrayList<Long> balancesInAMonth = new ArrayList<>();


    public ShortTermSavingAccount (Client accountHolder, String accountPIN,
                                   String internetBankingPassword, long balance) {
        super(accountHolder, accountPIN, internetBankingPassword, balance);
    }

    public ShortTermSavingAccount  (Client accountHolder, long uniqueIdentifier, String accountPIN, LocalDate dateOfOpeningTheAccount,
                                    String internetBankingPassword,int cvv2, long balance) {
        super(accountHolder, uniqueIdentifier, accountPIN, dateOfOpeningTheAccount, internetBankingPassword, cvv2, balance);

    }

    public ArrayList<Long> getBalancesInAMonth() {
        return balancesInAMonth;
    }

    //This method deposits money to an account :
    @Override
    public String deposit (int transferredMoney) {
        setBalance(getBalance() + transferredMoney);

        Transaction transaction = new Transaction("Deposit To ", transferredMoney, this);
        this.getTransactions().add(transaction);

        this.getBalancesInAMonth().add(this.getBalance());

        String message = transaction.toString();

        return message;
    }

    @Override
    public String toString () {
        return String.format("\nAccount Holder : %s %s\nUnique Identifier : %d , \nBalance : %d, \ncvv2 : %d\n",
                getAccountHolder().getFirstName(), getAccountHolder().getFamilyName(),
                getUniqueIdentifier(), getBalance() + interest(this), getCvv2());
    }

    //This method calculates and adds monthly interest :
    public int interest (ShortTermSavingAccount shortTermSavingAccount) {
        int interest = (int)(shortTermSavingAccount.leastBalanceInAMonth() *
                shortTermInterestRate * DateAndTime.howManyDays(this.getAccountHolder())) / 365;

        this.getBalancesInAMonth().clear();

        return interest;
    }

    //This method finds the least amount of money in the account :
    protected long leastBalanceInAMonth () {
        long max = 9223372036854775807L;   //Maximum amount of money in an account.

        for (int i = 0; i < this.getBalancesInAMonth().size(); i++) {
            if (this.getBalancesInAMonth().get(i) < max) {
                max = this.getBalancesInAMonth().get(i);
            }
        }

        return max;
    }
}
