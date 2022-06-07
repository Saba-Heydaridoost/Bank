package com.company;

import java.time.LocalDate;

public class LongTermSavingAccount extends Account {

    private final static double longTermInterestRateInAYear = 0.15;

    public LongTermSavingAccount(Client accountHolder, String accountPIN,
                                 String internetBankingPassword, long balance) {
        super(accountHolder, accountPIN, internetBankingPassword, balance);
    }

    public LongTermSavingAccount (Client accountHolder, long uniqueIdentifier, String accountPIN, LocalDate dateOfOpeningTheAccount,
                                  String internetBankingPassword,int cvv2, long balance) {
        super(accountHolder, uniqueIdentifier, accountPIN, dateOfOpeningTheAccount, internetBankingPassword, cvv2, balance);

    }

    @Override
    public String toString () {
        return String.format("\nAccount Holder : %s %s\nUnique Identifier : %d , \nBalance : %d, \ncvv2 : %d\n",
                getAccountHolder().getFirstName(), getAccountHolder().getFamilyName(),
                getUniqueIdentifier(), getBalance() + interest(this), getCvv2());
    }

    public String deposit(int transferredMoney) {
        String result = "You Can Not Add Money To A Long Term Saving Account !";

        return result;
    }

    //This method calculates and adds monthly interest :
    public int interest (Account account) {
        int interest = (int)(account.getBalance() * longTermInterestRateInAYear *
                DateAndTime.howManyDays(this.getAccountHolder())) / 365;

        return interest;
    }

}
