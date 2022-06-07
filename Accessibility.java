package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.Comparator;

public abstract class Accessibility implements Comparator <Person>{

    //Stage :
    static Stage window = new Stage();

    //Scene :
    static Scene accessibilityScene;

    private static ArrayList<Employee> employees = new ArrayList<Employee>();
    private static ArrayList<Client> clients = new ArrayList<Client>();

    public static String client = "Client";
    public static String employee = "Employee";
    public static String manager = "Manager";
    public static String admin = "Admin";
    public static String transaction = "Transaction";
    public static String account = "Account";

    public static ArrayList<Client> getClients() {
        return clients;
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    //Identity number getter :
    public static void identityNumberGetterScene (String whoIsIt, String whoAreYou) {
        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        //Entering identity number :
        Label identityNumberLabel = new Label("Identity Number :");
        TextField identityNumberInput = new TextField();

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        //Positioning label, textField and buttons :
        GridPane.setConstraints(identityNumberLabel, 0, 0);
        GridPane.setConstraints(identityNumberInput, 1, 0);
        GridPane.setConstraints(confirmButton, 0, 1);
        GridPane.setConstraints(cancelButton, 1, 1);

        //Adding everything to the layout :
        layout.getChildren().addAll(identityNumberInput, identityNumberLabel,
                confirmButton, cancelButton);

        //Scene :
        accessibilityScene = new Scene(layout, 300, 200);
        window.setScene(accessibilityScene);
        window.show();

        //Setting buttons on action :
        confirmButton.setOnAction(e -> {
            int index = searching(whoIsIt, identityNumberInput.getText());

            if (index != -1){
                if (whoIsIt.equals(client)) {
                    MessageScene.message(clients.get(index).toString(), whoAreYou);

                    ClientScene.clientProfile(whoAreYou);
                }

                else if (whoIsIt.equals(employee)){
                    MessageScene.message(employees.get(index).toString(), whoAreYou);

                    EmployeeScene.changingTheProfileOfEmployees(whoAreYou);
                }

                else if (whoIsIt.equals(transaction)) {
                    TransactionScene.showingAClientTransactions(index, whoAreYou);
                    TransactionScene.transactionsProfile(whoAreYou);
                }
                window.close();
            }
        });

        cancelButton.setOnAction(e -> {
            if (whoIsIt.equals(client)) {
                ClientScene.clientProfile(whoAreYou);
            }
            else if (whoIsIt.equals(employee)) {
                EmployeeScene.changingTheProfileOfEmployees(whoAreYou);
            }
            else if (whoIsIt.equals(account)) {
                AccountScene.accountProfile(whoAreYou);
            }
            else if (whoIsIt.equals(transaction)) {
                TransactionScene.transactionsProfile(whoAreYou);
            }
            window.close();
        });
    }

    //This method search the index of employee or client you want to delete :
    public static void personDeleterScene(String whoIsIt, String whoAreYou){

        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        //Entering identity number :
        Label identityNumberLabel = new Label("Identity Number :");
        TextField identityNumberInput = new TextField();

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        //Positioning label, textField and buttons :
        GridPane.setConstraints(identityNumberLabel, 0, 0);
        GridPane.setConstraints(identityNumberInput, 1, 0);
        GridPane.setConstraints(confirmButton, 0, 1);
        GridPane.setConstraints(cancelButton, 1, 1);

        //Adding everything to the layout :
        layout.getChildren().addAll(identityNumberInput, identityNumberLabel,
                confirmButton, cancelButton);

        //personDeleterScene :
        accessibilityScene = new Scene(layout, 300, 200);
        window.setScene(accessibilityScene);
        window.show();

        //Setting actions on buttons :
        confirmButton.setOnAction(e -> {
            int index = searching(whoIsIt, identityNumberInput.getText());

            if (index != -1) {
                if (whoIsIt.equals(employee)) {
                    deleting(index, whoAreYou);
                    savingInfoOfEmployeesToFile();

                    EmployeeScene.changingTheProfileOfEmployees(whoAreYou);
                }
                else if (whoIsIt.equals(client)) {
                    deletingClient(index, whoAreYou);
                    savingInfoOfClientsToFile();
                    savingInfoOfAccountToFile();
                    savingInfoOfTransactionsToFile();

                    ClientScene.clientProfile(whoAreYou);
                }
            }
            window.close();
        });

        cancelButton.setOnAction(e -> {
            if (whoIsIt.equals(employee)) {
                EmployeeScene.changingTheProfileOfEmployees(whoAreYou);
                window.close();
            }
        });
    }

    //This method search the index of employee or client you want to delete :
    public static void searchingForChanging(String whoIsIt, String whoAreYou){

        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        //Entering identity number :
        Label identityNumberLabel = new Label("Identity Number :");
        TextField identityNumberInput = new TextField();

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        //Positioning label, textField and buttons :
        GridPane.setConstraints(identityNumberLabel, 0, 0);
        GridPane.setConstraints(identityNumberInput, 1, 0);
        GridPane.setConstraints(confirmButton, 0, 1);
        GridPane.setConstraints(cancelButton, 1, 1);

        //Adding everything to the layout :
        layout.getChildren().addAll(identityNumberInput, identityNumberLabel,
                confirmButton, cancelButton);

        //Scene :
        accessibilityScene = new Scene(layout, 300, 200);
        window.setScene(accessibilityScene);
        window.show();

        //Setting actions on buttons :
        confirmButton.setOnAction(e -> {
            int index = searching(whoIsIt, identityNumberInput.getText());

            if (whoIsIt.equals(employee)) {
                if (index != -1) {
                    PersonScene.changingPersonInfo(Accessibility.employee, whoAreYou, index);
                    Accessibility.savingInfoOfEmployeesToFile();
                }
                else {
                    EmployeeScene.changingTheProfileOfEmployees(whoAreYou);
                }
            }

            else if (whoIsIt.equals(client)) {
                if (index != -1) {
                    PersonScene.changingPersonInfo(Accessibility.client, whoAreYou, index);
                    Accessibility.savingInfoOfClientsToFile();
                }
                else {
                    ClientScene.clientProfile(whoAreYou);
                }
            }

            window.close();
        });

        cancelButton.setOnAction(e -> {
            if (whoIsIt.equals(employee)) {
                EmployeeScene.changingTheProfileOfEmployees(whoAreYou);
            }
            else if (whoIsIt.equals(client)) {
                ClientScene.clientProfile(whoAreYou);
            }
            window.close();
        });
    }

    //Finding index :
    public static int searching (String whoIsIt, String identityNumber_) {
        int holder = -1;

        try {
            if(!identityNumber_.matches("[\\d]+")){
                throw new InputMismatchException();
            }
            try {
                boolean fnd = false;
                if (whoIsIt.equals(employee)) {
                    for (int i = 0; i < employees.size(); ++i){
                        if (employees.get(i).getIdentity_number().equals(identityNumber_)) {
                            fnd = true;
                            holder = i;
                            break;
                        }
                    }
                }
                else if (whoIsIt.equals(client) || whoIsIt.equals(account) || whoIsIt.equals(transaction)) {
                    for (int i = 0; i < clients.size(); ++i){
                        if (clients.get(i).getIdentity_number().equals(identityNumber_)) {
                            fnd = true;
                            holder = i;
                            break;
                        }
                    }
                }
                if (!fnd){
                    throw new ImproperInputException("");
                }
            } catch (Exception e) {
                System.out.println(TextColor.RED + "NO RESULT !!!\n" +
                        "The Identity Number Is Wrong Or It Does Not Belong To This Bank !\n" + TextColor.RESET);
            }
        } catch (InputMismatchException inputMismatchException){
            System.out.println(TextColor.RED + "The Identity Number Is Incorrect !\n" + TextColor.RESET);
        }

        return holder;
    }


    //deleting an employee...
    public static void deleting(int i, String whoAreYou){
        String result = String.format("Mr/Ms %s %s With Identity Number %s Is Removed Successfully."
                , employees.get(i).getFirstName(), employees.get(i).getFamilyName(),
                employees.get(i).getIdentity_number());

        employees.remove(i);

        MessageScene.message(result, whoAreYou);
    }

    //deleting a client...
    public static void deletingClient(int i, String whoAreYou){
        String result = String.format("Mr/Ms %s %s With Identity Number %s Is Removed Successfully."
                , clients.get(i).getFirstName(), clients.get(i).getFamilyName(),
                clients.get(i).getIdentity_number());

        clients.remove(i);

        MessageScene.message(result, whoAreYou);
    }

    //display the Client Into a File ...
    public static void displayTheClientIntoFile(String whoIsIt,String whoAreyou){
        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        //Entering identity number :
        Label identityNumberLabel = new Label("Identity Number :");
        TextField identityNumberInput = new TextField();

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        //Positioning label, textField and buttons :
        GridPane.setConstraints(identityNumberLabel, 0, 0);
        GridPane.setConstraints(identityNumberInput, 1, 0);
        GridPane.setConstraints(confirmButton, 0, 1);
        GridPane.setConstraints(cancelButton, 1, 1);

        //Adding everything to the layout :
        layout.getChildren().addAll(identityNumberInput, identityNumberLabel,
                confirmButton, cancelButton);

        //Scene :
        accessibilityScene = new Scene(layout, 300, 200);
        window.setScene(accessibilityScene);
        window.show();

        //Setting buttons on action :
        confirmButton.setOnAction(event -> {
            int index = searching(whoIsIt, identityNumberInput.getText());
            if (index != -1) {
                String output = "";
                output += clients.get(index).toString() + "\r\n=> The Accounts That Belong To The Client : <=\r\n";
                for (int j = 0; j < clients.get(index).getAccounts().size(); ++j){
                    output += clients.get(index).getAccounts().get(j).toString() + "\r\n";
                }

                try {
                    FileWriter writer = new FileWriter("C" + clients.get(index).getIdentity_number() + ".txt",
                            false);
                    writer.write(output);
                    writer.close();
                    MessageScene.message("\"The File Is Created Successfully!\"", whoAreyou);
                } catch (IOException e) {
                    e.getMessage();
                }
            }
            else {
                identityNumberInput.clear();
            }
            window.close();
        });

        cancelButton.setOnAction(event -> {
            ClientScene.clientProfile(whoAreyou);
            window.close();
        });
    }

    //Removing an account based on it's unique identifier :
    public static void accountDeleter (long uniqueIdentifier, String whoAreYou) {
        String result = "";
        boolean valid = false;
        for (int i = 0; i < clients.size(); i++) {
            for (int j = 0; j < clients.get(i).getAccounts().size(); j++) {
                if (clients.get(i).getAccounts().get(j).getUniqueIdentifier() == uniqueIdentifier) {
                    clients.get(i).getAccounts().remove(clients.get(i).getAccounts().get(j));

                    result = String.format("The Account With Unique Identifier %d is Removed Successfully,\n" +
                                    "This Account belonged To Mr/Ms %s %s.", uniqueIdentifier,
                            clients.get(i).getFirstName(), clients.get(i).getFamilyName());
                    valid = true;
                    break;
                }
            }
        }

        if (!valid) {
            result = "Account Not Found !";
        }

        MessageScene.message(result, whoAreYou);
        if (!(whoAreYou.equals(Accessibility.client))){
            AccountScene.accountProfile(whoAreYou);
        }
    }

    //Adding new account :
    public static void accountAdder (String whoAreYou) {
        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        //Entering identity number :
        Label identityNumberLabel = new Label("Identity Number :");
        TextField identityNumberInput = new TextField();

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        //Positioning label, textField and buttons :
        GridPane.setConstraints(identityNumberLabel, 0, 0);
        GridPane.setConstraints(identityNumberInput, 1, 0);
        GridPane.setConstraints(confirmButton, 0, 1);
        GridPane.setConstraints(cancelButton, 1, 1);

        //Adding everything to the layout :
        layout.getChildren().addAll(identityNumberInput, identityNumberLabel,
                confirmButton, cancelButton);

        //Scene :
        accessibilityScene = new Scene(layout, 300, 200);
        window.setScene(accessibilityScene);
        window.show();

        confirmButton.setOnAction(e -> {
            int index = -1;
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).getIdentity_number().equals(identityNumberInput.getText())) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                System.out.println(TextColor.RED + "Client Not Found !" + TextColor.RESET);
                AccountScene.accountProfile(whoAreYou);
            }
            else {
                Client client = clients.get(index);
                String message = String.format("You Are Creating Account For Mr/Ms %s %s ...\n",
                        client.getFirstName(), client.getFamilyName());
                MessageScene.message(message, whoAreYou);

                AccountScene.openingAccount(client, whoAreYou);

                window.close();
            }
            identityNumberInput.clear();
        } );

        cancelButton.setOnAction(e -> {
            AccountScene.accountProfile(whoAreYou);
            identityNumberInput.clear();
            window.close();
        });

    }

    public static void displayTheAccountIntoFile(String whoAreYou){
        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        //Label :
        Label label = new Label("Unique Identifier :");

        //TextField :
        TextField uniqueIdentifierInput = new TextField();

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        //Positioning everything :
        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(uniqueIdentifierInput, 1, 0);
        GridPane.setConstraints(confirmButton, 0, 1);
        GridPane.setConstraints(cancelButton, 1, 1);

        //Adding everything to the layout :
        layout.getChildren().addAll(label, uniqueIdentifierInput, confirmButton, cancelButton);

        //Scene :
        accessibilityScene = new Scene(layout, 300, 200);
        window.setScene(accessibilityScene);
        window.show();

        confirmButton.setOnAction(e -> {
            long UniqueIdentifier = Long.parseLong(uniqueIdentifierInput.getText());
            String output = "";
            boolean fnd = false;
            for (int i = 0; i < clients.size(); ++i){
                for (int j = 0; j < clients.get(i).getAccounts().size(); ++j){
                    if (clients.get(i).getAccounts().get(j).getUniqueIdentifier() == UniqueIdentifier) {
                        fnd = true;
                        output += clients.get(i).getAccounts().get(j).toString()
                                + "\r\n=> Transactions in this account : <=\r\n";
                        for (int t = 0; t < clients.get(i).getAccounts().get(j).getTransactions().size(); ++t) {
                            output += ((t + 1) + "- " + clients.get(i).getAccounts().get(j).getTransactions().get(t).toString())
                                    + "\r\n";
                        }
                        break;
                    }
                }
            }
            if (!fnd){
                System.out.println(TextColor.RED + "NO RESULT !!!\n" +
                        "The Unique-Identifier Is Wrong Or It Does Not Belong To This Bank !\n" + TextColor.RESET);
                uniqueIdentifierInput.clear();
            }
            else {
                try {
                    FileWriter writer = new FileWriter("A" + UniqueIdentifier + ".txt", false);
                    writer.write(output);
                    writer.close();
                    MessageScene.message("\"The File Is Created Successfully!\"", whoAreYou);
                } catch (IOException exception) {
                    exception.getMessage();
                }
            }
            window.close();
        });

        cancelButton.setOnAction(e -> AccountScene.accountProfile(whoAreYou));
    }

    //Searching for a specific transaction based on it's evidence code :
    public static void transactionSearcher (long evidenceCode, String whoAreYou) {
        String result = "";

        for (int i = 0; i < clients.size(); i++) {
            for (int j = 0; j < clients.get(i).getAccounts().size(); j++) {
                for (int k = 0; k < clients.get(i).getAccounts().get(j).getTransactions().size(); k++) {
                    if (clients.get(i).getAccounts().get(j).getTransactions().get(k).getEvidenceCode() == evidenceCode) {
                        result += clients.get(i).getAccounts().get(j).getTransactions().get(k).toString()+"\n";
                    }
                }
            }
        }

        if (result.length() == 0) {
            result = "Transaction Not Found !";
        }

        MessageScene.message(result, whoAreYou);
    }

    public static void gettingInfoOfEmployees () {
        String identityNumber = "", password = "", firstName = "", familyName = "", gender = "";
        LocalDate dateOfBirth;
        LocalDate dateOfRegister;

        File myFile = new File (".\\employee.csv");

        if (myFile.exists() || myFile.length() != 0) {
            try {
                Scanner myReader = new Scanner(myFile);

                while (myReader.hasNextLine()) {
                    String[] infoArr = myReader.nextLine().split(",");
                    identityNumber = infoArr[0];

                    firstName = infoArr[1];

                    familyName = infoArr[2];

                    password = infoArr[3];

                    String[] partsOfDate = infoArr[4].split("-");
                    dateOfBirth = LocalDate.of(Integer.parseInt(partsOfDate[0]),
                            Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2]));

                    gender = infoArr[5];

                    String[] partsOfDate1 = infoArr[7].split("-");
                    dateOfRegister = LocalDate.of(Integer.parseInt(partsOfDate1[0]),
                            Integer.parseInt(partsOfDate1[1]), Integer.parseInt(partsOfDate1[2]));

                    Employee employee = new Employee(identityNumber, firstName, familyName,
                            dateOfBirth, gender, dateOfRegister, password);

                    employee.setSalary(Long.parseLong(infoArr[6]));

                    employees.add(employee);
                }

                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("There Is Not Any File To Check The Info !\n" +
                        "May Be The Pathname Is Incorrect !");
            }
        }
    }

    public static void gettingInfoOfClients () {
        String identityNumber = "", password = "", firstName = "", familyName = "", gender = "";
        LocalDate dateOfBirth;
        LocalDate dateOfRegister;

        File myFile = new File (".\\clients.csv");

        if (myFile.exists() || myFile.length() != 0) {
            try {
                Scanner myReader = new Scanner(myFile);

                while (myReader.hasNextLine()) {
                    String[] infoArr = myReader.nextLine().split(",");
                    identityNumber = infoArr[0];

                    firstName = infoArr[1];

                    familyName = infoArr[2];

                    password = infoArr[3];

                    String[] partsOfDate = infoArr[4].split("-");
                    dateOfBirth = LocalDate.of(Integer.parseInt(partsOfDate[0]),
                            Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2]));

                    gender = infoArr[5];

                    String[] partsOfDate1 = infoArr[6].split("-");
                    dateOfRegister = LocalDate.of(Integer.parseInt(partsOfDate1[0]),
                            Integer.parseInt(partsOfDate1[1]), Integer.parseInt(partsOfDate1[2]));

                    Client client = new Client(identityNumber, firstName, familyName,
                            dateOfBirth, gender, dateOfRegister, password);

                    clients.add(client);
                }

                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println(TextColor.RED + "There Is Not Any File To Check The Info !\n" +
                        "May Be The Pathname Is Incorrect !" + TextColor.RESET);
            }
        }

    }

    public static void gettingInfoOfAccounts () {
        String identityNumber = "", accountPIN = "", internetBankingPassword = "", accountType = "";
        long balance = 0, uniqueIdentifier = 0;
        int cvv2, index = 0;
        LocalDate dateOfOpeningTheAccount = LocalDate.of(1, 1, 1);
        File myFile = new File (".\\accounts.csv");

        if (myFile.exists() || myFile.length() != 0) {
            try {
                Scanner myReader = new Scanner(myFile);

                while (myReader.hasNextLine()) {
                    String[] infoArr = myReader.nextLine().split(",");

                    identityNumber = infoArr[0];

                    uniqueIdentifier = Long.parseLong(infoArr[1]);

                    String[] partsOfDate = infoArr[2].split("-");
                    dateOfOpeningTheAccount = LocalDate.of(Integer.parseInt(partsOfDate[0]),
                            Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2]));

                    balance = Long.parseLong(infoArr[3]);

                    internetBankingPassword = infoArr[4];

                    accountPIN = infoArr[5];

                    cvv2 = Integer.parseInt(infoArr[6]);

                    accountType = infoArr[7];

                    Client client = new Client("0", "0",
                            "0", DateAndTime.getToday(),
                            "male", DateAndTime.getToday(), "0");

                    boolean clientExists = false;

                    for (int i = 0; i < clients.size(); i++) {
                        if (clients.get(i).getIdentity_number().equals(identityNumber)) {
                            client = clients.get(i);
                            index = i;
                            clientExists = true;
                        }
                    }
                    if (!clientExists) {
                        for (int i = 0; i < infoArr.length; i++) {
                            infoArr[i] = "0";
                        }
                    }

                    if (!infoArr[1].equals("0")) {
                        Account account;
                        if (accountType.equals("Checking Account")) {
                            account = new CheckingAccount(client, uniqueIdentifier, accountPIN, dateOfOpeningTheAccount,
                                    internetBankingPassword, cvv2, balance);
                        }
                        else if (accountType.equals("Short Term Saving Account")) {
                            account = new ShortTermSavingAccount(client, uniqueIdentifier, accountPIN, dateOfOpeningTheAccount,
                                    internetBankingPassword, cvv2, balance);
                        }
                        else {
                            account = new LongTermSavingAccount(client, uniqueIdentifier, accountPIN, dateOfOpeningTheAccount,
                                    internetBankingPassword, cvv2, balance);
                        }
                        clients.get(index).getAccounts().add(account);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("There Is Not Any File To Check The Info !\n" +
                        "May Be The Pathname Is Incorrect !");
            }
        }
    }

    public static void gettingInfoOfTransactions () {
        String type = "";
        long uniqueIdentifier, evidenceCode ;
        int  amount = 0, indexClient = 0, indexAccount = 0;;
        LocalDate date  ;
        ZonedDateTime time ;
        File myFile = new File (".\\transactions.csv");

        if (myFile.exists() || myFile.length() != 0) {
            try {
                Scanner myReader = new Scanner(myFile);

                while (myReader.hasNextLine()) {
                    String[] infoArr = myReader.nextLine().split(",");

                    uniqueIdentifier = Long.parseLong(infoArr[0]);

                    evidenceCode = Long.parseLong(infoArr[1]);

                    String[] partsOfDate = infoArr[2].split("-");
                    date = LocalDate.of(Integer.parseInt(partsOfDate[0]),
                            Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2]));

                    time = ZonedDateTime.parse((infoArr[3]));

                    type = infoArr[4];

                    amount = Integer.parseInt(infoArr[5]);

                    Account account = new Account(clients.get(0), 0, "0", DateAndTime.getToday(),
                            "0", 0, 0) {
                        @Override
                        public String deposit(int transferredMoney) {
                            return null;
                        }
                    };
                    boolean accountExists = false;
                    for (int i = 0; i < clients.size(); i++) {
                        for (int j=0; j<clients.get(i).getAccounts().size(); ++j){
                            if (clients.get(i).getAccounts().get(j).getUniqueIdentifier()==uniqueIdentifier) {
                                account = clients.get(i).getAccounts().get(j);
                                indexClient = i;
                                indexAccount = j;
                                accountExists = true;
                                break;
                            }
                        }
                    }
                    if (!accountExists) {
                        for (int i = 0; i < infoArr.length; i++) {
                            infoArr[i] = "0";
                        }
                    }

                    if (!infoArr[1].equals("0")) {
                        Transaction transaction;
                        transaction = new Transaction(account,evidenceCode,date,time,type,amount);
                        clients.get(indexClient).getAccounts().get(indexAccount).getTransactions().add(transaction);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("There Is Not Any File To Check The Info !\n" +
                        "May Be The Pathname Is Incorrect !");
            }
        }
    }

    //This is the first method that is called for transferring :
    public static void transferRequest (Account account1, Account account2) {

        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        //Label :
        Label amountLabel = new Label("Amount :");

        //TextFields :
        TextField amountInput = new TextField();

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        //Positioning everything :
        GridPane.setConstraints(amountLabel, 0, 0);
        GridPane.setConstraints(amountInput, 1, 0);
        GridPane.setConstraints(confirmButton, 0, 1);
        GridPane.setConstraints(cancelButton, 1, 1);

        //Adding to layout :
        layout.getChildren().addAll(amountLabel, amountInput, confirmButton, cancelButton);

        //Scene :
        accessibilityScene = new Scene(layout, 300, 200);
        window.setScene(accessibilityScene);
        window.show();


        //Setting buttons on actions :
        confirmButton.setOnAction(e -> {
            if (noExceptions(amountInput.getText(), account1)) {
                account1.withdraw(Integer.parseInt(amountInput.getText()), account1, account2);
            }
            window.close();
        });

        cancelButton.setOnAction(e -> {
            AccountScene.clientAccountsProfile(account1.getAccountHolder());
            window.close();
        });

    }

    public static boolean noExceptions (String amount, Account account1) {
        boolean isInt = false;
        try {
            if (!amount.matches("[\\d]+")) {
                throw new ImproperInputException("");
            }
            try {
                if (Long.parseLong(amount) >= (account1.getBalance() - Account.getLeastMoney())) {
                    throw new InsufficientFundsException("");
                }
                isInt = true;
            } catch (Exception exception) {
                System.out.println(TextColor.RED + "Insufficient Funds !\n" + TextColor.RESET );
            }
        } catch(Exception exception){
            System.out.println(TextColor.RED + "Enter A Proper Number !" + TextColor.RESET);
        }
        return isInt;
    }

    //This method does a transaction in a specific duration :
    public static void transferringPeriodically (Account account1, Account account2, int amount) {
        String result ;

        if (account1.getBalance() <
                (amount * DateAndTime.howManyMonths(DateAndTime.getToday()))
                        + Account.getLeastMoney()) {
            result = DateAndTime.getTime() +
                    String.format(" :\nUnique Identifier : %d" +
                                    "\nThere Is Not At Least %d Rials In The Account For Transferring Periodically." ,
                            account1.getUniqueIdentifier(), amount + Account.getLeastMoney());
        }
        else {
            account1.setBalance(account1.getBalance()
                    - (amount * DateAndTime.howManyMonths(DateAndTime.getToday()))
                    - Account.getTransferWithdraw());
            Transaction transaction1 = new Transaction("Transferring Periodically From ", amount, account1);
            result = TextColor.CYAN + "\n" + transaction1.toString() + TextColor.RESET;
            account1.getTransactions().add(transaction1);

            account2.setBalance(account2.getBalance()
                    + (amount * DateAndTime.howManyMonths(DateAndTime.getToday())));
            Transaction transaction2 = new Transaction("Transferring Periodically To ", amount, account2);
            account2.getTransactions().add(transaction2);
        }
        MessageScene.message(result, client);
        savingInfoOfTransactionsToFile();
        savingInfoOfAccountToFile();
    }

    //Saving employees info :
    public static void savingInfoOfEmployeesToFile(){
        ArrayList<String[]> csv = new ArrayList<>();

        for (int i = 0; i < Accessibility.getEmployees().size(); ++i){
            String row1[] = {Accessibility.getEmployees().get(i).getIdentity_number(),
                    Accessibility.getEmployees().get(i).getFirstName(),
                    Accessibility.getEmployees().get(i).getFamilyName(),
                    Accessibility.getEmployees().get(i).getPassword(),
                    String.valueOf(Accessibility.getEmployees().get(i).getDateOfBirth()),
                    Accessibility.getEmployees().get(i).getGender(),
                    String.valueOf(Accessibility.getEmployees().get(i).getSalary()),
                    String.valueOf(Accessibility.getEmployees().get(i).getDateOfRegister())};
            csv.add(row1);
        }
        Files.writeCSV(".\\employee.csv", csv);
    }

    //Saving clients info :
    public static void savingInfoOfClientsToFile(){
        ArrayList<String[]> csv = new ArrayList<>();

        for (int i = 0; i < Accessibility.getClients().size(); ++i){
            String row1[] = {Accessibility.getClients().get(i).getIdentity_number(),
                    Accessibility.getClients().get(i).getFirstName(),
                    Accessibility.getClients().get(i).getFamilyName(),
                    Accessibility.getClients().get(i).getPassword(),
                    String.valueOf(Accessibility.getClients().get(i).getDateOfBirth()),
                    Accessibility.getClients().get(i).getGender(),
                    String.valueOf(Accessibility.getClients().get(i).getDateOfRegister())};

            csv.add(row1);
        }
        Files.writeCSV(".\\clients.csv", csv);
    }

    //Writing to the accounts' file :
    public static void savingInfoOfAccountToFile() {

        ArrayList<String[]> csv1 = new ArrayList<>();

        for (int i = 0; i <clients.size(); ++i){
            for (int j = 0; j < clients.get(i).getAccounts().size(); ++j) {
                if (clients.get(i).getAccounts().get(j) instanceof CheckingAccount) {
                    String row1[] = {clients.get(i).getIdentity_number(),
                            String.valueOf(clients.get(i).getAccounts().get(j).getUniqueIdentifier()),
                            String.valueOf(clients.get(i).getAccounts().get(j).getDateOfOpeningTheAccount()),
                            String.valueOf(clients.get(i).getAccounts().get(j).getBalance()),
                            clients.get(i).getAccounts().get(j).getInternetBankingPassword(),
                            clients.get(i).getAccounts().get(j).getAccountPIN(),
                            String.valueOf(clients.get(i).getAccounts().get(j).getCvv2()),
                            "Checking Account"};
                    csv1.add(row1);
                }
                else if (clients.get(i).getAccounts().get(j) instanceof ShortTermSavingAccount) {
                    String row1[] = {clients.get(i).getIdentity_number(),
                            String.valueOf(clients.get(i).getAccounts().get(j).getUniqueIdentifier()),
                            String.valueOf(clients.get(i).getAccounts().get(j).getDateOfOpeningTheAccount()),
                            String.valueOf(clients.get(i).getAccounts().get(j).getBalance()),
                            clients.get(i).getAccounts().get(j).getInternetBankingPassword(),
                            clients.get(i).getAccounts().get(j).getAccountPIN(),
                            String.valueOf(clients.get(i).getAccounts().get(j).getCvv2()),
                            "Short Term Saving Account"};

                    csv1.add(row1);
                }
                else {
                    String row1[] = {clients.get(i).getIdentity_number(),
                            String.valueOf(clients.get(i).getAccounts().get(j).getUniqueIdentifier()),
                            String.valueOf(clients.get(i).getAccounts().get(j).getDateOfOpeningTheAccount()),
                            String.valueOf(clients.get(i).getAccounts().get(j).getBalance()),
                            clients.get(i).getAccounts().get(j).getInternetBankingPassword(),
                            clients.get(i).getAccounts().get(j).getAccountPIN(),
                            String.valueOf(clients.get(i).getAccounts().get(j).getCvv2()),
                            "Long Term Saving Account"};

                    csv1.add(row1);
                }
            }
        }
        Files.writeCSV(".\\accounts.csv", csv1);
    }

    //Writing to the transactions' file :
    public static void savingInfoOfTransactionsToFile(){
        ArrayList<String[]> csv = new ArrayList<>();

        for (int i = 0; i < clients.size(); ++i){
            for (int j = 0; j < clients.get(i).getAccounts().size(); ++j) {
                for (int k = 0; k < clients.get(i).getAccounts().get(j).getTransactions().size(); ++k) {
                    String row1[] = {String.valueOf(clients.get(i).getAccounts().get(j).getUniqueIdentifier()),
                            String.valueOf(clients.get(i).getAccounts().get(j).getTransactions().get(k).getEvidenceCode()),
                            String.valueOf(clients.get(i).getAccounts().get(j).getTransactions().get(k).getDate()),
                            String.valueOf(clients.get(i).getAccounts().get(j).getTransactions().get(k).getTime()),
                            clients.get(i).getAccounts().get(j).getTransactions().get(k).getType(),
                            String.valueOf(clients.get(i).getAccounts().get(j).getTransactions().get(k).getAmount())};
                    csv.add(row1);
                }
            }
        }
        Files.writeCSV(".\\transactions.csv", csv);

    }
}



