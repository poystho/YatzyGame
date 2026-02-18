package com.example.yatzygui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;


public class Yatzy extends Application {

    private Scorecard scorecard;
    private Integer[] currentDice;
    private int rollsRemaining = 3;
    private Label[] diceLabels = new Label[5];

    @Override
    public void start(Stage primaryStage) throws IOException {

        for (int i = 0; i < 5; i++) {
            diceLabels[i] = new Label("?");
            diceLabels[i].setStyle(
                    "-fx-font-size: 48px; " +
                            "-fx-min-width: 80px; " +
                            "-fx-min-height: 80px; " +
                            "-fx-border-color: black; " +
                            "-fx-border-width: 2px; " +
                            "-fx-background-color: white; " +
                            "-fx-alignment: center;"
            );
        }

        HBox diceBox = new HBox(15);
        diceBox.setAlignment(Pos.CENTER);

        for (int i = 0; i < 5; i++) {
            diceBox.getChildren().add(diceLabels[i]);
        }

        Button rollButton = new Button("Roll Dice");
        rollButton.setStyle("-fx-font-size: 18px");
        rollButton.setOnAction(e -> rollDice());

        // Layout
        VBox root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 40px; -fx-background-color: #f0f0f0;");
        root.getChildren().addAll(diceBox, rollButton);

        // Scene and stage
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("Yatzy");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void rollDice() {
        currentDice = DiceLogic.diceThrow();
        updateDiceDisplay();
        rollsRemaining--;
    }

    private void updateDiceDisplay() {
        for (int i = 0; i < currentDice.length; i++) {
            diceLabels[i].setText(String.valueOf(currentDice[i]));
        }
    }

    private void selectScore(String category) {

    }


}
