package com.company;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class LogInScene {

    //Entering identity number :
    public static Label identityNumberLabel = new Label("Identity Number :");
    public static TextField identityNumberInput = new TextField();

    //Entering password :
    public static Label passwordLabel = new Label("Password :");
    public static PasswordField passwordInput = new PasswordField();

    public static int counter = 0;

    //Scenes :
    static Scene logInScene;

    //Stage :
    static Stage window = new Stage();

    public static TextField getIdentityNumberInput() {
        return identityNumberInput;
    }

    public static void setIdentityNumberInput(String identityNumberInput) {
        LogInScene.identityNumberInput.setText(identityNumberInput);
        LogInScene.getIdentityNumberInput().clear();
    }

    public static TextField getPasswordInput() {
        return passwordInput;
    }

    public static void setPasswordInput(String passwordInput) {
        LogInScene.passwordInput.setText(passwordInput);
        LogInScene.getPasswordInput().clear();
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        LogInScene.counter = counter;
    }

    public static Scene getLogInScene() {
        return logInScene;
    }

    public static void setLogInScene(Scene logInScene) {
        LogInScene.logInScene = logInScene;
    }

    public static void logIn (String whoAreYou) {

       //GridPane :
       GridPane layout = new GridPane();
       layout.setPadding(new Insets(10, 10, 10, 10));
       layout.setVgap(8);
       layout.setHgap(10);

       //Creating button :
       Button loginButton = new Button("Log In");
       loginButton.setStyle("-fx-font-weight: bold;"+"-fx-background-color: linear-gradient(pink,indianred);");

       //Positioning labels and textFields :
       GridPane.setConstraints(identityNumberLabel, 0, 0);
       GridPane.setConstraints(identityNumberInput, 1, 0);
       GridPane.setConstraints(passwordLabel, 0, 1);
       GridPane.setConstraints(passwordInput, 1, 1);
       GridPane.setConstraints(loginButton, 1, 2);

       //Adding all things to the layout :
       layout.getChildren().addAll(identityNumberLabel, identityNumberInput, passwordLabel, passwordInput, loginButton);

       //Creating scene :
       logInScene = new Scene(layout, 300, 150);
       layout.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: Red;");
       window.setScene(logInScene);
       window.setTitle("Farabi Bank");
       window.getIcons().add(new Image(Main.class.getResourceAsStream("Keys.png")));
       window.show();

       //Setting action for the button :
       loginButton.setOnAction(e -> {
           if (onlyNumbers()) {
               LogInActions.enter(whoAreYou);
               window.close();
           }
           identityNumberInput.clear();
           passwordInput.clear();
       });
   }

    private static boolean onlyNumbers () {
        boolean properInput = true;
        try {
            if (!LogInScene.getIdentityNumberInput().getText().matches("[\\d]+")) {
                throw new ImproperInputException("");
            }
        } catch (Exception e) {
            System.out.println(TextColor.RED + "Identity Number Should Only Include Numbers !" + TextColor.RESET);
            properInput = false;
        }

        try {
            if (!LogInScene.getPasswordInput().getText().matches("[\\d]+")) {
                throw new ImproperInputException("");
            }
        } catch (Exception e) {
            System.out.println(TextColor.RED + "Password Should Only Include Numbers !" + TextColor.RESET);
            properInput = false;
        }

       return properInput;
   }
}
