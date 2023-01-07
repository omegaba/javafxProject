package com.example;

import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class hostJoinPanel extends GridPane{
    private Button host;
    private Button join;
    private hostJoinControler controler;
    private Button play;


    public void setControler(hostJoinControler hjc){
        this.controler=hjc;
    }

    public hostJoinPanel(hostJoinControler c){
        this.controler=c;
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #0a1931;");
        pane.setPrefHeight(450.0);
        pane.setPrefWidth(600.0);
        host = new Button("Launch as host");
        host.setId("play button");
        host.setAlignment(Pos.BOTTOM_RIGHT);
        host.setLayoutX(70.0);
        host.setLayoutY(204.0);
        host.setMnemonicParsing(false);
        host.prefHeight(35.0);
        host.prefWidth(110.0);
        host.setStyle("-fx-background-color: #185adb; -fx-cursor: hand;");
        host.setTextFill(Color.valueOf("#ffc947"));
        host.setFont(new Font(23.0));
        host.setOnAction(event -> {
            controler.playSettings(true);
        });
        pane.getChildren().add(host);

        join = new Button("Join a party");
        join.setId("play button");
        join.setAlignment(Pos.BOTTOM_LEFT);
        join.setLayoutX(350.0);
        join.setLayoutY(204.0);
        join.setMnemonicParsing(false);
        join.prefHeight(35.0);
        join.prefWidth(110.0);
        join.setStyle("-fx-background-color: #185adb; -fx-cursor: hand;");
        join.setTextFill(Color.valueOf("#ffc947"));
        join.setFont(new Font(23.0));
        join.setOnAction(event -> {
            controler.playSettings(false);
        });
        pane.getChildren().add(join);

        play = new Button("Menu");
        play.setId("playAgain");
        play.setLayoutX(250.0);
        play.setLayoutY(291.0);
        play.setMnemonicParsing(false);
        play.setOnAction(event -> {
            try {
                gameControler.toMainMenu();;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        pane.getChildren().add(play);
        this.getChildren().add(pane);
    }
}
