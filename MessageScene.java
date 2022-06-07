package com.company;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MessageScene {
    //Stage :
    static Stage window = new Stage();

    public static void message (String message_,String whoAreyou) {

        //Label :
        Label message = new Label(message_);

        //Button :
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
//            if (whoAreYou.equals(Accessibility.admin)) {
//                MenuScene.adminMenu();
//            }
//            else if (whoAreYou.equals(Accessibility.manager)) {
//                MenuScene.managerMenu();
//            }
//            else if (whoAreYou.equals(Accessibility.employee)) {
//                MenuScene.employeeMenu();
//
//            }
//            else if (whoAreYou.equals(Accessibility.client)) {
//               LogInScene.logIn(Accessibility.client);
//            }
            window.close();
        });

        //Layout :
        VBox layout = new VBox(10);
        layout.getChildren().addAll(message, closeButton);
        layout.setAlignment(Pos.CENTER);

        window.setTitle("Message");
        window.setMinWidth(300);

        //Creating scene :
        Scene scene = new Scene(layout, 500, 500);
        window.setTitle("Farabi Bank");
        layout.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color:red;"
        );
        window.setScene(scene);

        window.show();
    }

}
