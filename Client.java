package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client extends Person {

    public static final String ANSI_RESET = "\u001B[0m";    //    Colors for text :)))
    public static final String ANSI_RED = "\u001B[31m";     //


    private ArrayList <Account> accounts = new ArrayList<>();

    public Client (String identity_number, String firstName, String familyName, LocalDate dateOfBirth,
                   String gender, LocalDate dateOfRegister, String password) {
        super (identity_number, firstName, familyName,
                dateOfBirth, gender, dateOfRegister, password);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccounts(Account account) {
        this.accounts.add(account);
    }

    @Override
    public String toString () {
        return String.format("[Identity Number : %s,\nFirst Name : %s,\nFamily Name : %s,\n" +
                        "Date Of Birth : %s,\nGender : %s,\nDate Of Register : %s]",
                this.getIdentity_number(), this.getFirstName(), this.getFamilyName(),
                this.getDateOfBirth(), this.getGender(), this.getDateOfRegister());
    }

}
