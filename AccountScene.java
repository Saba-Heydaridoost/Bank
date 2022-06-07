
//********************Graphics**********************//

package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.InputMismatchException;

public class AccountScene {

    //Stage :
    static Stage window = new Stage();

    //personDeleterScene :
    static Scene accountsScene;

    static Long uniqueIdentifier_ = 0L;

    public static void accountProfile (String whoAreYou) {

        //VBox :
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        //Buttons :
        Button addingButton = new Button("Adding");
        Button deletingButton = new Button("Deleting");
        Button changingThePINButton = new Button("Changing The PIN");
        Button searchingButton = new Button("Searching For An Account");
        Button showingAllButton = new Button("Showing The List Of All Accounts");
        Button displayAccountsInAFileButton = new Button("Display An Account In A File");
        Button backButton = new Button("Back");

        //Adding everything to the layout :
        layout.getChildren().addAll(addingButton, deletingButton, changingThePINButton,
                searchingButton, showingAllButton, displayAccountsInAFileButton,
                backButton);

        //Scene :
        accountsScene = new Scene(layout, 400, 300);
        window.setScene(accountsScene);
        window.setTitle("Farabi Bank");
        window.show();

        //Setting actions on buttons :
        addingButton.setOnAction(e -> {
            Accessibility.accountAdder(whoAreYou);
            window.close();
        });

        deletingButton.setOnAction(e -> {
            try {
                if (Accessibility.getClients().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "No Client Exists Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (Accessibility.getClients().size() != 0) {
                gettingUniqueIdentifier(whoAreYou, Accessibility.account, "Delete");
            }
        });

        changingThePINButton.setOnAction(e -> {
            try {
                if (Accessibility.getClients().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "No Client Exists Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (Accessibility.getClients().size() != 0) {
                gettingUniqueIdentifier(whoAreYou, Accessibility.account, "Change");
            }
        });

        searchingButton.setOnAction(e -> {
            try {
                if (Accessibility.getClients().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "No Client Exists Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (Accessibility.getClients().size() != 0) {
                searchInTheListOfAccounts(whoAreYou);
            }
        });

        showingAllButton.setOnAction(e -> {
            try {
                if (Accessibility.getClients().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "No Client Exists Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (Accessibility.getClients().size() != 0) {
                displayListOfAllAccounts(whoAreYou);
            }
        });

        displayAccountsInAFileButton.setOnAction(e -> {
            try {
                if (Accessibility.getClients().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "No Client Exists Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (Accessibility.getClients().size() != 0) {
                Accessibility.displayTheAccountIntoFile(whoAreYou);
            }
        });

        backButton.setOnAction(e -> {
            if (whoAreYou.equals(Accessibility.admin)) {
                MenuScene.adminMenu();
            }
            else if (whoAreYou.equals(Accessibility.manager)) {
                MenuScene.managerMenu();
            }
            else if (whoAreYou.equals(Accessibility.employee)) {
                MenuScene.employeeMenu();
            }
            window.close();
        });
    }

    public static void clientAccountsProfile (Client client) {

        //VBox :
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        //Buttons :
        Button addingButton = new Button("Adding");
        Button deletingButton = new Button("Deleting");
        Button changingThePINButton = new Button("Changing The PIN Of A Specific Account");
        Button showingAllButton = new Button("Showing The List Of All Accounts");
        Button showingInfoButton= new Button("Showing Information Of A Specific Account");
        Button transferButton = new Button("Transferring Money");
        Button transferringPeriodicallyButton = new Button("Choosing Accounts For Transferring Periodically");
        Button gettingTransactionsButton = new Button("Getting Transactions Of A Specific Account");
        Button evidenceCodeButton = new Button("Getting A specific Transaction Based On It's Evidence Code");
        Button backButton = new Button("Back");

        //Adding everything to the layout :
        layout.getChildren().addAll(addingButton, deletingButton, changingThePINButton,
                showingAllButton, showingInfoButton, transferButton, transferringPeriodicallyButton,
                gettingTransactionsButton, evidenceCodeButton, backButton);

        //Scene :
        accountsScene = new Scene(layout, 500, 400);
        window.setScene(accountsScene);
        window.setTitle("Farabi Bank");
        window.show();

        //Setting actions on buttons :
        addingButton.setOnAction(e -> {
            openingAccount(client, Accessibility.client);
        });

        deletingButton.setOnAction(e -> {
            try {
                if (client.getAccounts().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "You Have No Account Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (client.getAccounts().size() != 0) {
                gettingUniqueIdentifierOfTheClientAccount(client, false,"deleting" );
            }
        });

        changingThePINButton.setOnAction(e -> {
            try {
                if (client.getAccounts().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "You Have No Account Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (client.getAccounts().size() != 0) {
                gettingUniqueIdentifierOfTheClientAccount(client, false,"changingThePIN" );
            }
        });

        showingAllButton.setOnAction(e -> {
            try {
                if (client.getAccounts().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "You Have No Account Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (client.getAccounts().size() != 0) {
                String result = "";
                for (int i = 0; i < client.getAccounts().size(); i++) {
                    result += client.getAccounts().get(i).toString() + "\n";
                }

                MessageScene.message(result, Accessibility.client);
            }
        });

        showingInfoButton.setOnAction(e -> {
            try {
                if (client.getAccounts().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "You Have No Account Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (client.getAccounts().size() != 0) {
                gettingUniqueIdentifierOfTheClientAccount(client, false,"showingInfo" );
            }
        });

        transferButton.setOnAction(e -> {
            try {
                if (client.getAccounts().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "You Have No Account Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (client.getAccounts().size() != 0) {
                transferringMoney(client, "Transfer");
            }
        });

        transferringPeriodicallyButton.setOnAction(e -> {
            try {
                if (client.getAccounts().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "You Have No Account Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (client.getAccounts().size() != 0) {
                transferringMoney(client, "TP");
            }
        });

        gettingTransactionsButton.setOnAction(e -> {
            try {
                if (client.getAccounts().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "You Have No Account Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (client.getAccounts().size() != 0) {
                gettingUniqueIdentifierOfTheClientAccount(client, false,"transactions" );
            }
        });

        evidenceCodeButton.setOnAction(e -> {
            try {
                if (client.getAccounts().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "You Have No Account Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (client.getAccounts().size() != 0) {
                TransactionScene.evidenceCodeGetterScene(Accessibility.client, client);
                window.close();
            }
        });

        backButton.setOnAction(e -> {
            MenuScene.clientMenu(client.getIdentity_number());
            window.close();
        });
    }

    //Showing the list of clients :
    public static void displayTheAccountsOfAClient (String whoAreYou){

        //GridPane :
        GridPane layout1 = new GridPane();
        layout1.setPadding(new Insets(10, 10, 10, 10));
        layout1.setVgap(8);
        layout1.setHgap(10);

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
        layout1.getChildren().addAll(identityNumberInput, identityNumberLabel,
                confirmButton, cancelButton);

        //Scene :
        accountsScene = new Scene(layout1, 300, 200);
        window.setScene(accountsScene);
        window.show();

        //Setting actions on buttons :
        confirmButton.setOnAction(e -> {
            int index = Accessibility.searching(Accessibility.client, identityNumberInput.getText());

            if (index != -1) {
                if (Accessibility.getClients().get(index).getAccounts().size() == 0) {
                    String message = String.format("Mr/Ms %s %s Has No Account In This Bank Yet !",
                            Accessibility.getClients().get(index).getFirstName(),
                            Accessibility.getClients().get(index).getFamilyName());

                    MessageScene.message(message, whoAreYou);
                }
                else {

                    TableView<Account> accountTableView;
                    //Unique Identifier column :
                    TableColumn<Account, String> uniqueIdentifierColumn = new TableColumn<>("Unique Identifier");
                    uniqueIdentifierColumn.setMinWidth(200);
                    uniqueIdentifierColumn.setCellValueFactory(new PropertyValueFactory<>("uniqueIdentifier"));

                    //Date Of Opening The Account column :
                    TableColumn<Account, String> dateColumn = new TableColumn<>("Date Of Opening The Account");
                    dateColumn.setMinWidth(200);
                    dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfOpeningTheAccount"));

                    //Balance column :
                    TableColumn<Account, String> balanceColumn = new TableColumn<>("Balance");
                    balanceColumn.setMinWidth(200);
                    balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

                    //Button :
                    Button closeButton = new Button("Close");

                    //Setting action on button :
                    closeButton.setOnAction(event -> {
                        ClientScene.clientProfile(whoAreYou);
                        window.close();
                    });

                    //Full Name Label :
                    Label fullNameLabel = new Label(Accessibility.getClients().get(index).getFirstName() +
                            " " + Accessibility.getClients().get(index).getFamilyName());

                    //Table :
                    accountTableView = new TableView<>();
                    accountTableView.setItems(getObservableAccounts(index));
                    accountTableView.getColumns().addAll(uniqueIdentifierColumn, dateColumn, balanceColumn);

                    VBox layout = new VBox(10);
                    layout.getChildren().addAll(fullNameLabel, accountTableView, closeButton);

                    Scene displayTable = new Scene(layout);
                    window.setScene(displayTable);
                    window.setTitle("Farabi Bank");
                    window.show();
                }
            }
            else {
                identityNumberInput.clear();
                displayTheAccountsOfAClient(whoAreYou);
            }
            ClientScene.clientProfile(whoAreYou);
        });

        cancelButton.setOnAction(e -> {
            ClientScene.clientProfile(whoAreYou);
            window.close();
        });
    }


    //Get all of the products
    private static ObservableList<Account> getObservableAccounts(int index) {
        ObservableList<Account> accounts = FXCollections.observableArrayList();

        accounts.addAll(Accessibility.getClients().get(index).getAccounts());

        return accounts;
    }

    //This method get the unique identifier of an account  :
    public static void gettingUniqueIdentifier(String whoAreYou, String whichProfile, String action){
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

        //Scene and stage :
        accountsScene= new Scene(layout, 300, 200);
        window.setScene(accountsScene);
        window.setTitle("Farabi Bank");
        window.show();

        //Setting actions on buttons :
        confirmButton.setOnAction(e -> {
            if (noException(uniqueIdentifierInput.getText())) {
                if (whichProfile.equals(Accessibility.account)) {
                    uniqueIdentifier_ = Long.parseLong(uniqueIdentifierInput.getText());

                    if (action.equals("Delete")) {
                        if (uniqueIdentifier_ != 0) {
                            Accessibility.accountDeleter(uniqueIdentifier_, whoAreYou);
                            Accessibility.savingInfoOfTransactionsToFile();
                            Accessibility.savingInfoOfAccountToFile();
                        }
                    }

                    else if (action.equals("Change")) {
                        if (uniqueIdentifier_ != 0) {
                            for (int i = 0; i < Accessibility.getClients().size(); i++) {
                                for (int j = 0; j < Accessibility.getClients().get(i).getAccounts().size(); j++) {
                                    if (Accessibility.getClients().get(i).getAccounts().get(j).getUniqueIdentifier()
                                            == uniqueIdentifier_) {
                                        String message = String.format("You Are Changing Mr/Ms %s %s With" +
                                                        " Identity Number %s", Accessibility.getClients().get(i).getFirstName()
                                                , Accessibility.getClients().get(i).getFamilyName(),
                                                Accessibility.getClients().get(i).getIdentity_number());

                                        MessageScene.message(message, whoAreYou);
                                        //Fake Client :
                                        Client client = new Client("0", "0", "0",
                                                DateAndTime.getToday(), "0", DateAndTime.getToday(), "0");
                                        PINChangerScene(uniqueIdentifier_, whoAreYou, client);

                                        break;
                                    }
                                }
                            }
                        }
                        else {
                            uniqueIdentifier_ = 0L;
                        }
                    }
                }
                else if (whichProfile.equals(Accessibility.transaction)) {
                    window.close();
                    TransactionScene.showingAnAccountTransactions(
                            Long.parseLong(uniqueIdentifierInput.getText()), whoAreYou);
                    TransactionScene.transactionsProfile(whoAreYou);
                }
            }
            else {
                uniqueIdentifierInput.clear();
                if (whichProfile.equals(Accessibility.account)){
                    window.close();
                    accountProfile(whoAreYou);
                }
                else if (whichProfile.equals(Accessibility.transaction)){
                    window.close();
                    TransactionScene.transactionsProfile(whoAreYou);
                }
            }
        });

        cancelButton.setOnAction(e -> {
            if (whichProfile.equals(Accessibility.account)){
                window.close();
                accountProfile(whoAreYou);
            }
            else if (whichProfile.equals(Accessibility.transaction)){
                window.close();
                TransactionScene.transactionsProfile(whoAreYou);
            }
        });
    }

    //This method get the unique Identifier of an account based on it's owner :
    public static void gettingUniqueIdentifierOfTheClientAccount(Client client, boolean UIForAnother, String action) {
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
        accountsScene = new Scene(layout, 300, 200);
        window.setScene(accountsScene);
        window.setTitle("Farabi Bank");
        window.show();

        confirmButton.setOnAction(e -> {
            if (noException(uniqueIdentifierInput.getText(), client, UIForAnother)) {
                uniqueIdentifier_ = Long.parseLong(uniqueIdentifierInput.getText());
                if (action.equals("transactions")){
                    String result = "";

                    for (int i = 0; i < client.getAccounts().size(); i++) {
                        if (client.getAccounts().get(i).getUniqueIdentifier() == uniqueIdentifier_) {
                            for (int j = 0; j < client.getAccounts().get(i).getTransactions().size(); j++) {
                                result += (j + 1) + "- " + client.getAccounts().get(i).getTransactions().get(j).toString()+"\n";
                            }
                            MessageScene.message(result, Accessibility.client);
                            clientAccountsProfile(client);
                            break;
                        }
                    }
                }
                else if (action.equals("deleting")) {
                    Accessibility.accountDeleter(uniqueIdentifier_, Accessibility.client);
                    Accessibility.savingInfoOfTransactionsToFile();
                    Accessibility.savingInfoOfAccountToFile();
                    clientAccountsProfile(client);
                }

                else if (action.equals("changingThePIN")) {
                    if (uniqueIdentifier_ != 0) {
                        for (int i = 0; i < client.getAccounts().size(); i++) {
                            if (client.getAccounts().get(i).getUniqueIdentifier() == uniqueIdentifier_) {
                                PINChangerScene(uniqueIdentifier_, Accessibility.client, client);
                                Accessibility.savingInfoOfAccountToFile();
                                Accessibility.savingInfoOfTransactionsToFile();
                                break;
                            }
                        }
                    }
                }

                else if (action.equals("showingInfo")) {
                    String result = "";

                    if (uniqueIdentifier_ != 0) {
                        for (int i = 0; i < client.getAccounts().size(); i++) {
                            if (client.getAccounts().get(i).getUniqueIdentifier() == uniqueIdentifier_) {
                                result += client.getAccounts().get(i).toString() + "\n";
                                break;
                            }
                        }
                    }
                    MessageScene.message(result, Accessibility.client);
                    clientAccountsProfile(client);
                }

                uniqueIdentifier_ = 0L;
            }
            else {
                uniqueIdentifierInput.clear();
            }

        });

        cancelButton.setOnAction(e -> clientAccountsProfile(client));

    }

    private static boolean noException (String UniqueIdentifier_, Client client, boolean UIForAnother) {
        boolean properInput = false;

        try {
            if(!UniqueIdentifier_.matches("[\\d]+")){
                throw new InputMismatchException();
            }
            try {   //Checks if any account with this unique identifier exists or not
                if (UIForAnother) {
                    for (int i = 0; i < Accessibility.getClients().size(); i++) {
                        for (int j = 0; j < Accessibility.getClients().get(i).getAccounts().size(); j++) {
                            if (Accessibility.getClients().get(i).getAccounts().get(j).getUniqueIdentifier()
                                    == Long.parseLong(UniqueIdentifier_)) {
                                properInput = true;
                                break;
                            }
                        }
                    }
                } else if (!UIForAnother) {
                    for (int i = 0; i < client.getAccounts().size(); i++) {
                        if (client.getAccounts().get(i).getUniqueIdentifier() == Long.parseLong(UniqueIdentifier_)) {
                            properInput = true;
                        }
                    }
                }
                if (!properInput){
                    throw new InputMismatchException();
                }
            } catch (Exception e) {
                System.out.println(TextColor.RED + "Unique Identifier Is Invalid !\n" + TextColor.RESET);
            }
        }catch (Exception e){
            System.out.println(TextColor.RED + "The Unique Identifier Is Incorrect !\n" + TextColor.RESET);
        }

        return properInput;
    }

    private static boolean noException (String UniqueIdentifier_) {
        boolean properInput = false;

        try {
            if(!UniqueIdentifier_.matches("[\\d]+")){
                throw new InputMismatchException();
            }
            try {   //Checks if any account with this unique identifier exists or not
                for (int i = 0; i < Accessibility.getClients().size(); i++) {
                    for (int j = 0; j < Accessibility.getClients().get(i).getAccounts().size(); j++) {
                        if (Accessibility.getClients().get(i).getAccounts().get(j).getUniqueIdentifier()
                                == Long.parseLong(UniqueIdentifier_)) {
                            properInput = true;
                        }
                    }
                }
                if (!properInput) {
                    throw new ImproperInputException("");
                }
            } catch (Exception e) {
                System.out.println(TextColor.RED + "Unique Identifier Is Invalid !\n" + TextColor.RESET);
            }
        }catch (Exception e){
            System.out.println(TextColor.RED + "The Unique Identifier Is Incorrect !\n" + TextColor.RESET);
        }

        return properInput;
    }

    //Creating new account :
    public static void openingAccount (Client client, String whoAreYou) {

        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");


        //Choosing type :
        ComboBox <String> accountType = new ComboBox<>();
        accountType.getItems().addAll(
                "1.Short Term Saving Account" ,
                "2.Long Term Saving Account",
                "3.Checking Account"
        );

        accountType.getSelectionModel().selectFirst();

        //Labels :
        Label accountTypeLabel = new Label("Account Type :");
        Label PINLabel = new Label("A 4-Digit PIN For Your New Account :");
        Label internetBankingPasswordLabel = new Label("A 5-To-8-Digit Internet Banking Password :");
        Label balanceLabel = new Label("Amount Of Money You Want To Pay In\n(It Must Be At Least 100000 Rials)");

        //Text fields :
        PasswordField PINInput = new PasswordField();
        PasswordField internetBankingPasswordInput = new PasswordField();
        TextField balanceInput = new TextField();

        //Positioning everything :
        GridPane.setConstraints(accountTypeLabel, 0, 0);
        GridPane.setConstraints(accountType, 1, 0);
        GridPane.setConstraints(PINLabel, 0, 1);
        GridPane.setConstraints(PINInput, 1, 1);
        GridPane.setConstraints(internetBankingPasswordLabel, 0, 2);
        GridPane.setConstraints(internetBankingPasswordInput, 1, 2);
        GridPane.setConstraints(balanceLabel, 0, 3);
        GridPane.setConstraints(balanceInput, 1, 3);
        GridPane.setConstraints(confirmButton, 0, 4);
        GridPane.setConstraints(cancelButton, 1, 4);

        //Adding everything to the layout :
        layout.getChildren().addAll(cancelButton , confirmButton, accountTypeLabel, internetBankingPasswordLabel,
                balanceLabel, PINLabel, PINInput, balanceInput, internetBankingPasswordInput,
                accountType);

        //Setting actions on buttons :
        confirmButton.setOnAction(e -> {
            if (noOpeningAccountExceptions(PINInput.getText(), internetBankingPasswordInput.getText(),
                    balanceInput.getText())) {

                Account account ;
                String result = "";

                if (accountType.getValue().equals("1.Short Term Saving Account")) {
                    account = new ShortTermSavingAccount(client, PINInput.getText(),
                            internetBankingPasswordInput.getText(), Long.parseLong(balanceInput.getText()));
                    result = DateAndTime.getToday() + String.format(
                            " :\nA New Short Term Account Is Created Successfully" +
                                    "\n For Mr/Ms %s %s With Identity Number %s.",
                            client.getFirstName(), client.getFamilyName(), client.getIdentity_number());
                }

                else if (accountType.getValue().equals("2.Long Term Saving Account")) {
                    account = new LongTermSavingAccount(client, PINInput.getText(),
                            internetBankingPasswordInput.getText(), Long.parseLong(balanceInput.getText()));
                    result = DateAndTime.getToday() + String.format(
                            " :\nA New Long Term Account Is Created Successfully" +
                                    "\n For Mr/Ms %s %s With Identity Number %s.",
                            client.getFirstName(), client.getFamilyName(), client.getIdentity_number());
                }

                else {
                    account = new CheckingAccount(client, PINInput.getText(),
                            internetBankingPasswordInput.getText(), Long.parseLong(balanceInput.getText()));
                    result = DateAndTime.getToday() + String.format(
                            " :\nA New Checking Account Is Created Successfully" +
                                    "\n For Mr/Ms %s %s With Identity Number %s.",
                            client.getFirstName(), client.getFamilyName(), client.getIdentity_number());
                }
                result += "\n\n" + account.toString();

                client.getAccounts().add(account);
                Accessibility.savingInfoOfAccountToFile();

                MessageScene.message(result, whoAreYou);
                window.close();
                if (whoAreYou.equals(Accessibility.client)){
                    clientAccountsProfile(client);
                }
                else{
                    accountProfile(whoAreYou);
                }
            }
            else{
                PINInput.clear();
                internetBankingPasswordInput.clear();
                balanceInput.clear();
                openingAccount(client,whoAreYou);
            }
        });
        cancelButton.setOnAction(event -> {
            PINInput.clear();
            internetBankingPasswordInput.clear();
            balanceInput.clear();
            if (whoAreYou.equals(Accessibility.client)){
                clientAccountsProfile(client);
            }
            else {
                accountProfile(whoAreYou);
            }
        });

        //Scene and stage :
        accountsScene = new Scene(layout, 400, 500);
        window.setScene(accountsScene);
        window.setTitle("Farabi Bank");
        window.show();
    }


    private static boolean noOpeningAccountExceptions (String accountPIN, String internetBankingPassword,
                                                       String balance) {
        boolean properInput = true;

        try {
            if (accountPIN.length() != 4 || !accountPIN.matches("\\d+")) {
                throw new ImproperInputException("");
            }
        } catch (Exception e) {
            System.out.println(TextColor.RED + "Enter A Proper Number For PIN !" + TextColor.RESET);
            properInput = false;
        }


        try {
            if (internetBankingPassword.length()  < 5 || internetBankingPassword.length() > 8
                    || !internetBankingPassword.matches("\\d+")) {
                throw new ImproperInputException("");
            }
        } catch (Exception e) {
            System.out.println(TextColor.RED +
                    "Enter A Proper Number For Internet Banking Password !" + TextColor.RESET);
            properInput = false;
        }


        try {
            if (!balance.matches("[\\d]+")) {
                throw new ImproperInputException("");
            }
            try {
                if (Long.parseLong(balance) < Account.getLeastMoney()) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException e) {
                System.out.println(TextColor.RED + "Balance Can Not Be Less Than 100000 Rials !" + TextColor.RESET);
                properInput = false;
            }
        } catch (ImproperInputException e) {
            System.out.println(TextColor.RED + "Enter A Proper Number For Balance !" + TextColor.RESET);
            properInput = false;
        }

        return properInput;
    }

    //Changing account's PIN based on unique identifier of the account :
    public static void PINChangerScene(long uniqueIdentifier, String whoAreYou, Client client) {

        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        //Label :
        Label label = new Label("The New Account PIN :\n(It Must Be A 4-Digit Number)");

        //TextField :
        PasswordField PINInput = new PasswordField();

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        //Positioning everything :
        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(PINInput, 1, 0);
        GridPane.setConstraints(confirmButton, 0, 1);
        GridPane.setConstraints(cancelButton, 1, 1);

        //Adding everything to the layout :
        layout.getChildren().addAll(label, PINInput, confirmButton, cancelButton);

        //Scene :
        accountsScene = new Scene(layout, 400, 200);
        window.setScene(accountsScene);
        window.setTitle("Farabi Bank");
        window.show();

        //Setting buttons on action :
        confirmButton.setOnAction(e -> {
            boolean properInput = false;
            for (int i = 0; i < Accessibility.getClients().size(); i++) {
                for (int j = 0; j < Accessibility.getClients().get(i).getAccounts().size(); j++) {
                    if (Accessibility.getClients().get(i).getAccounts().get(j).getUniqueIdentifier() ==
                    uniqueIdentifier) {
                        String accountPIN = "";
                        try {
                            accountPIN += PINInput.getText();

                            if (accountPIN.length() != 4 || !accountPIN.matches("\\d+")) {
                                throw new ImproperInputException("");
                            }
                            properInput = true;
                        } catch (Exception exception) {
                            System.out.println(TextColor.RED + "Enter A Proper Number !" + TextColor.RESET);
                            PINInput.clear();
                        }

                        if (properInput) {
                            Accessibility.getClients().get(i).getAccounts().get(j).setAccountPIN(accountPIN);
                            Accessibility.savingInfoOfAccountToFile();
                            Accessibility.savingInfoOfTransactionsToFile();
                            String message = "Changes Are Applied !";
                            MessageScene.message(message, whoAreYou);

                            break;
                        }
                    }
                }
            }
            if (whoAreYou.equals(Accessibility.client)){
                clientAccountsProfile(client);
            }
            else{
                accountProfile(whoAreYou);
            }
        });

        cancelButton.setOnAction(e -> {
            if (whoAreYou.equals(Accessibility.client)) {
                clientAccountsProfile(client);
            }
            else {
                AccountScene.accountProfile(whoAreYou);
            }
        });
    }

    public static void searchInTheListOfAccounts(String whoAreYou){

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
        accountsScene = new Scene(layout, 300, 150);
        window.setScene(accountsScene);
        window.show();

        confirmButton.setOnAction(e -> {
            boolean proper = true;
            try {
                if (!uniqueIdentifierInput.getText().matches("[\\d]+")) {
                    throw new ImproperInputException("");
                }
            } catch (Exception exception) {
                System.out.println(TextColor.RED +
                        "Unique Identifier Should Only Include Numbers !" + TextColor.RESET);
                uniqueIdentifierInput.clear();
                proper = false;
            }

            if (proper) {
                uniqueIdentifier_ = Long.parseLong(uniqueIdentifierInput.getText());
                boolean fnd = false;

                for (int i = 0; i < Accessibility.getClients().size(); ++i) {
                    for (int j = 0; j < Accessibility.getClients().get(i).getAccounts().size(); ++j) {

                        if (Accessibility.getClients().get(i).getAccounts().get(j).getUniqueIdentifier() == uniqueIdentifier_) {
                            fnd = true;
                            String message = Accessibility.getClients().get(i).getAccounts().get(j).toString();

                            MessageScene.message(message, whoAreYou);
                            break;
                        }
                    }
                }

                if (!fnd) {
                    System.out.println(TextColor.RED + "NO RESULT\n" +
                           "Your uniqueIdentifier is wrong or it doesn't belong to this Bank!\n" + TextColor.RESET);
                }
            }
            accountProfile(whoAreYou);
        });

        cancelButton.setOnAction(e -> {
            accountProfile(whoAreYou);
        });
    }

    //Showing the list of all accounts :
    public static void displayListOfAllAccounts(String whoAreYou) {
        Collections.sort(Accessibility.getClients(), (client1, client2) -> {
            int compareValue = client1.familyName.compareToIgnoreCase(client2.familyName);

            if (compareValue == 0) {
                return client1.firstName.compareToIgnoreCase(client2.firstName);
            }
            return compareValue;
        });

        VBox layout = new VBox(10);

        for (int i = 0; i < Accessibility.getClients().size(); ++i) {
            if (Accessibility.getClients().get(i).getAccounts().size() != 0) {
                String content = "";

                content += Accessibility.getClients().get(i).getAccounts().toString();

                Label accounts = new Label(content);

                layout.getChildren().addAll(accounts);
            }
        }

        //Buttons :
        Button closeButton = new Button("Close");
       layout.getChildren().add(closeButton);

        //Setting actions on buttons :
        closeButton.setOnAction(e -> accountProfile(whoAreYou));

        Scene displayTable = new Scene(layout);
        window.setScene(displayTable);
        window.setTitle("Farabi Bank");
        window.show();
    }

    public static void transferringMoney (Client client, String action) {

        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        //Label :
        Label account1label = new Label("The First Account Unique Identifier :");
        Label account2label = new Label("The Second Account Unique Identifier :");

        //TextField :
        TextField account1Input = new TextField();
        TextField account2Input = new TextField();

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        //Positioning everything :
        GridPane.setConstraints(account1label, 0, 0);
        GridPane.setConstraints(account2label, 0, 1);
        GridPane.setConstraints(account1Input, 1, 0);
        GridPane.setConstraints(account2Input, 1, 1);
        GridPane.setConstraints(confirmButton, 0, 2);
        GridPane.setConstraints(cancelButton, 1, 2);

        //Adding everything to the layout :
        layout.getChildren().addAll(account1label, account2label, account1Input,
                account2Input, confirmButton, cancelButton);

        //Scene :
        accountsScene = new Scene(layout, 400, 300);
        window.setScene(accountsScene);
        window.setTitle("Farabi Bank");
        window.show();

        //Setting actions on buttons :
        confirmButton.setOnAction(e -> {
            if (noException (account1Input.getText(), client, false)
                    && noException (account2Input.getText(), client, true)) {
                int index1 = -1, indexOfClient = -1, indexOfAccount = -1;

                for (int i = 0; i < client.getAccounts().size(); i++) {
                    if (client.getAccounts().get(i).getUniqueIdentifier()
                            == Long.parseLong(account1Input.getText())) {
                        index1 = i;
                    }
                }

                for (int i = 0; i < Accessibility.getClients().size(); i++) {
                    for (int j = 0; j < Accessibility.getClients().get(i).getAccounts().size(); j++) {
                        if (Accessibility.getClients().get(i).getAccounts().get(j).getUniqueIdentifier()
                                == Long.parseLong(account2Input.getText())) {
                            indexOfAccount = j;
                            indexOfClient = i;
                            break;
                        }
                    }
                }
                if (action.equals("Transfer")) {
                    Accessibility.transferRequest(client.getAccounts().get(index1),
                            Accessibility.getClients().get(indexOfClient).getAccounts().get(indexOfAccount));
                }
                else if (action.equals("TP")) {   //Transferring periodically
                    amountGetter(client, index1, indexOfAccount, indexOfClient);
                }
            }
            else {
                transferringMoney(client,action);
            }
        });

        cancelButton.setOnAction(e -> {
            clientAccountsProfile(client);
        });
    }

    public static void amountGetter (Client client, int index1, int indexOfAccount, int indexOfClient) {

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
        accountsScene = new Scene(layout, 300, 200);
        window.setScene(accountsScene);
        window.setTitle("Farabi Bank");
        window.show();

        //Setting buttons on actions :
        confirmButton.setOnAction(e -> {
            if (Accessibility.noExceptions(amountInput.getText(), client.getAccounts().get(index1))) {
                Accessibility.transferringPeriodically(client.getAccounts().get(index1),
                        Accessibility.getClients().get(indexOfClient).getAccounts().get(indexOfAccount),
                        Integer.parseInt(amountInput.getText()));

                window.close();
            }
        });

        cancelButton.setOnAction(e -> {
            AccountScene.clientAccountsProfile(client.getAccounts().get(index1).getAccountHolder());
        });
    }

    //This method does the transfer :
    public static void doTransfer (int transferredMoney, Account account1, Account account2) {

        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        //Label :
        Label whoLabel = new Label(String.format("The Account You choose Belongs To %s %s\n" ,
                account2.getAccountHolder().firstName, account2.getAccountHolder().familyName));
        Label cvv2Label = new Label("Enter your cvv2: ");
        Label internetBankingPasswordLabel = new Label("Enter your internet-Banking Password: ");

        //TextFields :
        TextField cvv2tInput = new TextField();
        PasswordField internetBankingPasswordInput = new PasswordField();

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        //Positioning everything :
        GridPane.setConstraints(whoLabel, 0, 0);
        GridPane.setConstraints(cvv2Label, 0, 1);
        GridPane.setConstraints(internetBankingPasswordLabel, 0, 2);
        GridPane.setConstraints(cvv2tInput, 1, 1);
        GridPane.setConstraints(internetBankingPasswordInput, 1, 2);
        GridPane.setConstraints(confirmButton, 0, 3);
        GridPane.setConstraints(cancelButton, 1, 3);

        //Adding to layout :
        layout.getChildren().addAll(whoLabel, cvv2Label, internetBankingPasswordLabel,
                cvv2tInput, internetBankingPasswordInput, confirmButton, cancelButton);

        //Scene :
        accountsScene = new Scene(layout, 400, 500);
        window.setScene(accountsScene);
        window.setTitle("Farabi Bank");
        window.show();

        //Setting buttons on actions :
        confirmButton.setOnAction(e -> {
            if (noTransferringExceptions(cvv2tInput.getText(), internetBankingPasswordInput.getText(), account1)) {
                //Transfer happens :
                account1.setBalance(account1.getBalance() - transferredMoney - Account.getTransferWithdraw());
                account2.deposit(transferredMoney);

                Transaction transaction = new Transaction("Withdrawing From ", transferredMoney, account1);
                account1.getTransactions().add(transaction);

                if (account1 instanceof ShortTermSavingAccount) {
                    ((ShortTermSavingAccount) account1).getBalancesInAMonth().add(account1.getBalance());
                }

                MessageScene.message(transaction.toString(), Accessibility.client);

                Accessibility.savingInfoOfTransactionsToFile();
                Accessibility.savingInfoOfAccountToFile();

                clientAccountsProfile(account1.getAccountHolder());
            }
        });

        cancelButton.setOnAction(e -> {
            clientAccountsProfile(account1.getAccountHolder());
            window.close();
        });

    }

    public static boolean noTransferringExceptions (String cvv2, String internetBankingPassword, Account account1) {
        boolean infoCorrect = false;

        try {
            if (!cvv2.matches("[\\d]+")) {
                throw new ImproperInputException("");
            }
            try {
                if (Integer.parseInt(cvv2) != account1.getCvv2()) {
                    throw new ImproperInputException("");
                }
                infoCorrect = true;
            } catch (Exception e) {
                System.out.println(TextColor.RED + "cvv2 Is Wrong !\n" + TextColor.RESET);
            }
        } catch (Exception e) {
            System.out.println(TextColor.RED + "cvv2 Is Wrong !\n" + TextColor.RESET);
        }

        try {
            if (!internetBankingPassword.matches("[\\d]+")) {
                throw new ImproperInputException("");
            }
            try {
                if (!internetBankingPassword.equals(account1.getInternetBankingPassword())) {
                    throw new ImproperInputException("");
                }
                infoCorrect = true;
            } catch (Exception e) {
                System.out.println(TextColor.RED + "Internet Banking Password Is Wrong !\n" +  TextColor.RESET);
            }
        } catch (Exception e) {
            System.out.println(TextColor.RED + "Internet Banking Password Is Wrong !\n" +  TextColor.RESET);
        }

        return infoCorrect;
    }

}
