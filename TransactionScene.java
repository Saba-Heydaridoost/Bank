package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TransactionScene {

    //Stage :
    static Stage window = new Stage();

    //Scene :
    static Scene transactionScene;

    public static void transactionsProfile(String whoAreYou) {
        //VBox :
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        //Buttons :
        Button showingAnAccountTransactionsButton = new Button("Showing An Account Transactions");
        Button showingAClientTransactionsButton = new Button("Showing A Client's Transactions");
        Button showingAllButton = new Button("Showing All Transactions");
        Button searchingEvidenceCodeButton = new Button("Searching For A Transaction Base On Evidence Code");
        Button backButton = new Button("Back");

        //Adding everything to the layout :
        layout.getChildren().addAll(showingAnAccountTransactionsButton, showingAClientTransactionsButton,
                showingAllButton, searchingEvidenceCodeButton, backButton);

        //Scene :
        transactionScene = new Scene(layout, 400, 300);
        window.setScene(transactionScene);
        window.setTitle("Farabi Bank");
        window.show();


        //Setting actions on buttons :
        showingAnAccountTransactionsButton.setOnAction(e -> {
            AccountScene.gettingUniqueIdentifier(whoAreYou,
                    Accessibility.transaction, "Transaction");

            window.close();
        });

        showingAClientTransactionsButton.setOnAction(e -> {
            showingAClientTransactions(Accessibility.client,whoAreYou);

        });

        showingAllButton.setOnAction(e -> {
            showingAllTransactions(whoAreYou);
        });

        searchingEvidenceCodeButton.setOnAction(e -> {
            //Fake Client :
            Client client = new Client("", "", "", DateAndTime.getToday(),
                    "", DateAndTime.getToday(), "");
            evidenceCodeGetterScene(whoAreYou, client);
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

    private static void showingAClientTransactions(String whoIsIt,String whoAreYou) {
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
        transactionScene = new Scene(layout, 300, 200);
        window.setScene(transactionScene);
        window.show();

        confirmButton.setOnAction(e -> {
            int index = Accessibility.searching(whoIsIt, identityNumberInput.getText());
            String result = "";
            if (index != -1){
                for (int j = 0; j < Accessibility.getClients().get(index).getAccounts().size(); j++) {
                    for (int k = 0; k < Accessibility.getClients().get(index).getAccounts().get(j).getTransactions().size(); k++) {
                        result += "[- " + Accessibility.getClients().get(index).getAccounts().get(j).getTransactions().get(k).toString() + "]\n";
                    }
                }
                if (result.equals("")){
                    result = "No Result!";
                }
                MessageScene.message(result, whoAreYou);
                window.close();
            }
            else {
                identityNumberInput.clear();
            }
            TransactionScene.transactionsProfile(whoAreYou);
        });

        cancelButton.setOnAction(e -> {
            window.close();
            TransactionScene.transactionsProfile(whoAreYou);
        });

    }

    //Showing a specific account's transactions based on it's unique identifier :
    public static void showingAnAccountTransactions(long uniqueIdentifier, String whoAreYou) {
        String result = "";

        for (int i = 0; i < Accessibility.getClients().size(); i++) {
            for (int j = 0; j < Accessibility.getClients().get(i).getAccounts().size(); j++) {
                if (Accessibility.getClients().get(i).getAccounts().get(j).getUniqueIdentifier() == uniqueIdentifier) {
                    for (int k = 0; k < Accessibility.getClients().get(i).getAccounts().get(j).getTransactions().size(); k++) {
                        result += "\n[ "+Accessibility.getClients().get(i).getAccounts().get(j).getTransactions().get(k).toString()+" ]\n";
                    }
                }
            }
        }

        if (result.length() == 0) {
            result = TextColor.RED + "NO RESULT!\n" +
                    "May Be Unique Identifier Is Wrong Or This Account Has Not any transactions yet!" + TextColor.RESET;
        }
        MessageScene.message(result, whoAreYou);
    }

    //Showing a specific client's transactions based on identity number :
    public static void showingAClientTransactions(int index, String whoAreYou) {
        String result = "";

        for (int j = 0; j < Accessibility.getClients().get(index).getAccounts().size(); j++) {
            for (int k = 0; k < Accessibility.getClients().get(index).getAccounts().get(j).getTransactions().size(); k++) {
                result += Accessibility.getClients().get(index).getAccounts().get(j).getTransactions().get(k).toString();
            }
        }

        if (result.length() == 0) {
            result = TextColor.RED + "NO RESULT!\n" +
                    "May Be Unique Identifier Is Wrong Or This Account Has Not Any Transactions Yet!" + TextColor.RESET;
        }
        MessageScene.message(result, whoAreYou);
    }

    //Showing all transactions :
    public static void showingAllTransactions(String whoAreYou) {
        String result = "";

        for (int i = 0; i < Accessibility.getClients().size(); i++) {
            for (int j = 0; j < Accessibility.getClients().get(i).getAccounts().size(); j++) {
                for (int k = 0; k < Accessibility.getClients().get(i).getAccounts().get(j).getTransactions().size(); k++) {
                    result +="[-"+ Accessibility.getClients().get(i).getAccounts().get(j).getTransactions().get(k).toString()+" ]\n";
                }
            }
        }

        MessageScene.message(result, whoAreYou);
    }

    public static void evidenceCodeGetterScene(String whoAreYou, Client client) {

        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        //Entering identity number :
        Label evidenceCodeLabel = new Label("Evidence Code :");
        TextField evidenceCodeInput = new TextField();

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");

        //Positioning label, textField and buttons :
        GridPane.setConstraints(evidenceCodeLabel, 0, 0);
        GridPane.setConstraints(evidenceCodeInput, 1, 0);
        GridPane.setConstraints(confirmButton, 0, 1);
        GridPane.setConstraints(cancelButton, 1, 1);

        //Adding everything to the layout :
        layout.getChildren().addAll(evidenceCodeInput, evidenceCodeLabel,
                confirmButton, cancelButton);

        //Scene :
        transactionScene = new Scene(layout, 300, 200);
        window.setScene(transactionScene);
        window.setTitle("Farabi Bank");
        window.show();

        //Setting actions on buttons :
        confirmButton.setOnAction(e -> {
            if (noException(evidenceCodeInput.getText())) {
                window.close();
                Accessibility.transactionSearcher(Long.parseLong(evidenceCodeInput.getText()), whoAreYou);
                if (whoAreYou.equals(Accessibility.client)) {
                    AccountScene.clientAccountsProfile(client);
                }
                else {
                    TransactionScene.transactionsProfile(whoAreYou);
                }
            }
            else{
                evidenceCodeInput.clear();
            }
        });

        cancelButton.setOnAction(e -> {
            if (whoAreYou.equals(Accessibility.client)) {
                AccountScene.clientAccountsProfile(client);
            }
            else {
                TransactionScene.transactionsProfile(whoAreYou);
            }
        });
    }

    public static boolean noException(String evidenceCode) {
        boolean proper = true;
        try {
            if (!evidenceCode.matches("[\\d]+")) {
                throw new ImproperInputException("");
            }
        } catch (Exception e) {
            System.out.println(TextColor.RED + "Evidence Code Should Only Includes Number ! " + TextColor.RESET);
            proper = false;
        }

        return proper;
    }
}

