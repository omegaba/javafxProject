package com.example;

import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class normalModeSettingsLaucher extends GridPane {
    private Button playWwords;
    private Button playWtime;
    private normalModeControler nmc;
    private Button play;
    public void setControler(normalModeControler n){
        this.nmc=n;
    }

    public normalModeSettingsLaucher(normalModeControler n) {
        this.nmc=n;
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #0a1931;");
        pane.setPrefHeight(450.0);
        pane.setPrefWidth(600.0);
        playWwords = new Button("Play with a word limit");
        playWwords.setId("play button");
        playWwords.setAlignment(Pos.BOTTOM_RIGHT);
        playWwords.setLayoutX(60.0);
        playWwords.setLayoutY(204.0);
        playWwords.setMnemonicParsing(false);
        playWwords.prefHeight(35.0);
        playWwords.prefWidth(110.0);
        playWwords.setStyle("-fx-background-color: #185adb; -fx-cursor: hand;");
        playWwords.setTextFill(Color.valueOf("#ffc947"));
        playWwords.setFont(new Font(23.0));
        playWwords.setOnAction(event -> {
            nmc.playSettings(true);
        });
        pane.getChildren().add(playWwords);

        playWtime = new Button("Play with a timer");
        playWtime.setId("play button");
        playWtime.setAlignment(Pos.BOTTOM_LEFT);
        playWtime.setLayoutX(350.0);
        playWtime.setLayoutY(204.0);
        playWtime.setMnemonicParsing(false);
        playWtime.prefHeight(35.0);
        playWtime.prefWidth(110.0);
        playWtime.setStyle("-fx-background-color: #185adb; -fx-cursor: hand;");
        playWtime.setTextFill(Color.valueOf("#ffc947"));
        playWtime.setFont(new Font(23.0));
        playWtime.setOnAction(event -> {
            nmc.playSettings(false);
        });
        pane.getChildren().add(playWtime);

        play = new Button("Menu");
        play.setId("playAgain");
        play.setLayoutX(250.0);
        play.setLayoutY(291.0);
        play.setMnemonicParsing(false);
        play.setOnAction(event -> {
            try {
                gameControler.toMainMenu();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        pane.getChildren().add(play);
        this.getChildren().add(pane);
    }

}
