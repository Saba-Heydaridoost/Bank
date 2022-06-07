package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuScene {

    //Scenes :
    static Scene menuScene;

    //Stage :
    static Stage window = new Stage();

    public static void adminMenu () {

        //VBox :
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        //Buttons :
        Button managerButton = new Button("Things To Do About The Manager");
        Button employeesButton = new Button("Things To Do About An Employee");
        Button clientsButton = new Button("Things To Do About A Client");
        Button accountsButton = new Button("Things To Do About An Account");
        Button transactionsButton = new Button("Getting Some Specific Transactions");
        Button exitButton = new Button("Exit");

        //Adding to the layout :
        layout.getChildren().addAll(managerButton, employeesButton, clientsButton,
                accountsButton, transactionsButton, exitButton);

        //Buttons actions :
        managerButton.setOnAction(e -> {
            PersonScene.changingPersonInfo(Accessibility.manager, Accessibility.admin, -1);

            window.close();
        });

        employeesButton.setOnAction(e -> {
            EmployeeScene.changingTheProfileOfEmployees(Accessibility.admin);

            window.close();
        });

        clientsButton.setOnAction(e -> {
            ClientScene.clientProfile(Accessibility.admin);

            window.close();
        });

        accountsButton.setOnAction(e -> {
            AccountScene.accountProfile(Accessibility.admin);

            window.close();
        });

        transactionsButton.setOnAction(e -> {
            TransactionScene.transactionsProfile(Accessibility.admin);

            window.close();
        });

        exitButton.setOnAction(e -> {
            String message = TextColor.MAGENTA + "*** Have A Nice Day ! ***\n   *** Farabi Bank ***" + TextColor.RESET;
            System.out.println(message);

            window.close();
            System.exit(0);
        });

        //Scene :
        menuScene = new Scene(layout, 300 , 250);
        window.setScene(menuScene);
        window.getIcons().add(new Image(Main.class.getResourceAsStream("Menu.png")));
        layout.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: yellow;" +
                "-fx-background-color:linear-gradient(#f4f565,white)  ;"
        );
        window.setTitle("Farabi Bank");
        window.show();
    }

    public static void managerMenu () {
        //VBox :
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        //Buttons :
        Button employeesButton = new Button("Things To Do About An Employee");
        Button clientsButton = new Button("Things To Do About A Client");
        Button accountsButton = new Button("Things To Do About An Account");
        Button transactionsButton = new Button("Getting Some Specific Transactions");
        Button exitButton = new Button("Exit");

        //Adding to the layout :
        layout.getChildren().addAll(employeesButton, clientsButton, accountsButton, transactionsButton, exitButton);

        window.getIcons().add(new Image(Main.class.getResourceAsStream("Menu.png")));
        //Buttons actions :
        employeesButton.setOnAction(e -> {
            EmployeeScene.changingTheProfileOfEmployees(Accessibility.manager);

            window.close();
        });

        clientsButton.setOnAction(e -> {
            ClientScene.clientProfile(Accessibility.manager);

            window.close();
        });

        accountsButton.setOnAction(e -> {
            AccountScene.accountProfile(Accessibility.manager);

            window.close();
        });

        transactionsButton.setOnAction(e -> {
            TransactionScene.transactionsProfile(Accessibility.manager);

            window.close();
        });

        exitButton.setOnAction(e -> {
            String message = TextColor.MAGENTA + "*** Have A Nice Day ! ***\n   *** Farabi Bank ***" + TextColor.RESET;
            System.out.println(message);

            window.close();

            System.exit(0);
        });

        //Scene :
        menuScene = new Scene(layout, 400 , 300);
        window.setScene(menuScene);
        layout.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: yellow;" +
                "-fx-background-color:linear-gradient(#f4f565,white)  ;"
        );
        window.setTitle("Farabi Bank");
        window.show();
    }

    public static void employeeMenu () {

        //VBox :
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        //Buttons :
        Button clientsButton = new Button("Things To Do About A Client");
        Button accountsButton = new Button("Things To Do About An Account");
        Button transactionsButton = new Button("Getting Some Specific Transactions");
        Button exitButton = new Button("Exit");

        //Adding to the layout :
        layout.getChildren().addAll(clientsButton, accountsButton, transactionsButton, exitButton);

        //Buttons actions :
        clientsButton.setOnAction(e -> {
            ClientScene.clientProfile(Accessibility.employee);

            window.close();
        });

        accountsButton.setOnAction(e -> {
            AccountScene.accountProfile(Accessibility.employee);

            window.close();
        });

        transactionsButton.setOnAction(e -> {
            TransactionScene.transactionsProfile(Accessibility.employee);

            window.close();
        });

        exitButton.setOnAction(e -> {
            String message = TextColor.MAGENTA + "*** Have A Nice Day ! ***\n   *** Farabi Bank ***" + TextColor.RESET;
            System.out.println(message);

            window.close();
            System.exit(0);
        });

        //Scene :
        menuScene = new Scene(layout, 500 , 200);
        window.setScene(menuScene);
        window.getIcons().add(new Image(Main.class.getResourceAsStream("Menu.png")));
        layout.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: yellow;" +
                "-fx-background-color:linear-gradient(#f4f565,white)  ;"
        );
        window.setTitle("Farabi Bank");
        window.show();
    }

    public static void clientMenu (String identityNumber) {

        //Vbox :
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        //Buttons :
        Button accountsButton = new Button("Things To Do About An Account");
        Button exitButton = new Button("Exit");

        //Adding to the layout :
        layout.getChildren().addAll(accountsButton, exitButton);

        //Buttons actions :
        accountsButton.setOnAction(e -> {
            Client client = new Client("0", "0", "0",
                    DateAndTime.getToday(), "male", DateAndTime.getToday(), "0");

            for (int i = 0; i < Accessibility.getClients().size(); i++) {
                if (Accessibility.getClients().get(i).getIdentity_number().equals(identityNumber)) {
                    client = Accessibility.getClients().get(i);
                }
            }
            AccountScene.clientAccountsProfile(client);

            window.close();
        });

        exitButton.setOnAction(e -> {
            String message = TextColor.MAGENTA + "*** Have A Nice Day ! ***\n   *** Farabi Bank ***" + TextColor.RESET;
            System.out.println(message);

            window.close();
            System.exit(0);
        });

        //Scene :
        menuScene = new Scene(layout, 300 , 100);
        window.setScene(menuScene);
        window.getIcons().add(new Image(Main.class.getResourceAsStream("Menu.png")));
        layout.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: yellow;" +
                "-fx-background-color:linear-gradient(#f4f565,white)  ;"
        );
        window.setTitle("Farabi Bank");
        window.show();
    }
}
