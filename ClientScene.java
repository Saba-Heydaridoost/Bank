
//********************Graphics**********************//

package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Comparator;

public class ClientScene {
    //Stage :
    static Stage window = new Stage();

    //personDeleterScene :
    static Scene clientScene;

    //Things we can do about a clint :
    public static void clientProfile (String whoAreYou) {

        //GridPane :
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        //Buttons :
        Button addingButton = new Button("Adding");
        Button deletingButton = new Button("Deleting");
        Button changingTheInformationButton = new Button("Changing The Information");
        Button searchingButton = new Button("Searching For A Client");
        Button showingAllButton = new Button("Showing The List Of All Clients");
        Button clientAccountsButton = new Button("Searching For Accounts Of A Client");
        Button displayClientInAFileButton = new Button("Display A Client In A File");
        Button backButton = new Button("Back");

        //Adding everything to the layout :
        layout.getChildren().addAll(addingButton, deletingButton, changingTheInformationButton,
                searchingButton, showingAllButton, clientAccountsButton, displayClientInAFileButton, backButton);

        //Setting actions on buttons :
        addingButton.setOnAction(e -> {
            PersonScene.addingPersonScene(Accessibility.client, whoAreYou);

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
                Accessibility.personDeleterScene(Accessibility.client, whoAreYou);
            }
        });

        changingTheInformationButton.setOnAction(e -> {
            try {
                if (Accessibility.getClients().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "No Client Exists Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (Accessibility.getClients().size() != 0) {
                Accessibility.searchingForChanging(Accessibility.client, whoAreYou);
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
                Accessibility.identityNumberGetterScene(Accessibility.client, whoAreYou);
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
                displayTheClients(whoAreYou);
            }
        });

        clientAccountsButton.setOnAction(e -> {
            try {
                if (Accessibility.getClients().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "No Client Exists Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (Accessibility.getClients().size() != 0) {
                AccountScene.displayTheAccountsOfAClient(whoAreYou);
            }
        });

        displayClientInAFileButton.setOnAction(e -> {
            try {
                if (Accessibility.getClients().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "No Client Exists Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (Accessibility.getClients().size() != 0) {
                Accessibility.displayTheClientIntoFile(Accessibility.client,whoAreYou);
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

        clientScene = new Scene(layout, 300, 300);
        window.setScene(clientScene);
        window.setTitle("Farabi Bank");
        window.show();
    }

    //Showing the list of clients :
    public static void displayTheClients (String whoAreYou){
        Collections.sort(Accessibility.getClients(), (client1, client2) -> {
            int compareValue = client1.familyName.compareToIgnoreCase(client2.familyName);

            if (compareValue == 0) {
                return client1.firstName.compareToIgnoreCase(client2.firstName);
            }

            return compareValue;
        });

        TableView<Client> clientTableView;

        //First name column :
        TableColumn<Client, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setMinWidth(200);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        //Family name column :
        TableColumn<Client, String> familyNameColumn = new TableColumn<>("Family Name");
        familyNameColumn.setMinWidth(200);
        familyNameColumn.setCellValueFactory(new PropertyValueFactory<>("familyName"));

        //Identity number column :
        TableColumn<Client, String> identity_numberColumn = new TableColumn<>("Identity Number");
        identity_numberColumn.setMinWidth(200);
        identity_numberColumn.setCellValueFactory(new PropertyValueFactory<>("identity_number"));

        //Date Of birth column :
        TableColumn<Client, String> dateOfBirthColumn = new TableColumn<>("Date Of Birth");
        dateOfBirthColumn.setMinWidth(200);
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        //Date of register column :
        TableColumn<Client, String> dateOfRegisterColumn = new TableColumn<>("Date Of Register");
        dateOfRegisterColumn.setMinWidth(200);
        dateOfRegisterColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfRegister"));

        //Table :
        clientTableView = new TableView<>();
        clientTableView.setItems(getObservableClients());
        clientTableView.getColumns().addAll(firstNameColumn, familyNameColumn, identity_numberColumn,
                dateOfBirthColumn, dateOfRegisterColumn);

        //Button :
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            clientProfile(whoAreYou);
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(clientTableView, closeButton);

        Scene displayTable = new Scene(layout);
        window.setScene(displayTable);
        window.setTitle("Farabi Bank");
        window.show();

    }

    //Get all of the products
    private static ObservableList<Client> getObservableClients() {
        ObservableList<Client> clients = FXCollections.observableArrayList();

        clients.addAll(Accessibility.getClients());

        return clients;
    }
}
