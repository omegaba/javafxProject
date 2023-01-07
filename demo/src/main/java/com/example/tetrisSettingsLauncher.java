package com.example;

import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class tetrisSettingsLauncher  extends GridPane{
    
    private Button solo;
    private Button multi;
    private tetrisSettingsControler tc;
    private Button play;

    public void setControler(tetrisSettingsControler t){
        this.tc=t;
    }

    public tetrisSettingsLauncher(tetrisSettingsControler settings){
        this.tc=settings;
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #0a1931;");
        pane.setPrefHeight(450.0);
        pane.setPrefWidth(600.0);
        solo = new Button("Play solo");
        solo.setId("play button");
        solo.setAlignment(Pos.BOTTOM_RIGHT);
        solo.setLayoutX(70.0);
        solo.setLayoutY(204.0);
        solo.setMnemonicParsing(false);
        solo.prefHeight(35.0);
        solo.prefWidth(110.0);
        solo.setStyle("-fx-background-color: #185adb; -fx-cursor: hand;");
        solo.setTextFill(Color.valueOf("#ffc947"));
        solo.setFont(new Font(23.0));
        solo.setOnAction(event -> {
            tc.playSettings(true);
        });
        pane.getChildren().add(solo);

        multi = new Button("Play multi");
        multi.setId("play button");
        multi.setAlignment(Pos.BOTTOM_LEFT);
        multi.setLayoutX(350.0);
        multi.setLayoutY(204.0);
        multi.setMnemonicParsing(false);
        multi.prefHeight(35.0);
        multi.prefWidth(110.0);
        multi.setStyle("-fx-background-color: #185adb; -fx-cursor: hand;");
        multi.setTextFill(Color.valueOf("#ffc947"));
        multi.setFont(new Font(23.0));
        multi.setOnAction(event -> {
            tc.playSettings(false);
        });
        pane.getChildren().add(multi);

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



