package com.company;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import static javafx.application.Application.launch;

/**
 * @author Faezeh Khadem & Saba Heidaridoost :)
 */

public class Main extends Application{

    public static Stage window;
    public static Scene whoAreYouScene;

    public static Stage getWindow() {
        return window;
    }

    public static void main(String[] args) {
        launch(args);
        //Action.enter();
    }

    public static Scene getWhoAreYouScene() {
        return whoAreYouScene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Farabi Bank");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("Bank.png")));

        //Creating the comboBox :
        ComboBox <String> choices = new ComboBox<>();
        //Adding choices :
        choices.getItems().addAll(
                Accessibility.admin,
                Accessibility.manager,
                Accessibility.employee,
                Accessibility.client
        );

        choices.getSelectionModel().selectFirst();

        //Creating the confirm button :
        Button button = new Button("Confirm");
        button.setStyle("-fx-font-weight: bold;");
        button.setOnAction(e -> {
            LogInScene.logIn(choices.getValue());
            window.close();
        });

        //Layout :
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(choices, button);
        layout.setAlignment(Pos.CENTER);

        //Creating the first scene :
        whoAreYouScene = new Scene(layout, 200, 200);
        layout.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color:blue;"+
                "-fx-background-color:linear-gradient(whitesmoke,darkblue)  ;"
                );
        window.setScene(whoAreYouScene);
        window.show();
    }
}
