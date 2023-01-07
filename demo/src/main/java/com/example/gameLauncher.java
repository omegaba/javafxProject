package com.example;
import java.io.File;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class gameLauncher extends GridPane {

    private gameLaucherController glc;
    private Label displayWelcome;
    private Button playNormal;
    private Button playTetris;
    private Button lastGame;
    
    public gameLauncher(gameLaucherController gc) throws IOException {
        this.glc =gc; 
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        Pane pane = new Pane();

        pane.setStyle("-fx-background-color: #0a1931;");
        pane.setPrefHeight(450.0);
        pane.setPrefWidth(600.0);


        displayWelcome= new Label("Welcome");
        displayWelcome.setId("display welcome");
        displayWelcome.setTextAlignment(TextAlignment.CENTER);
        displayWelcome.setTextFill(Color.WHITE);  
        displayWelcome.setAlignment(Pos.CENTER);
        displayWelcome.setLayoutX(200.6);
        displayWelcome.setLayoutY(49.9);
        displayWelcome.prefHeight(57.0);
        displayWelcome.prefWidth(267.0);
        displayWelcome.setFont(new Font(35.0));
        pane.getChildren().add(displayWelcome);



        playTetris=new Button("Play tetris mode");
        playTetris.setId("play button");
        playTetris.setAlignment(Pos.BOTTOM_RIGHT);
        playTetris.setLayoutX(70.0);
        playTetris.setLayoutY(204.0);
        playTetris.setMnemonicParsing(false);
        playTetris.prefHeight(35.0);
        playTetris.prefWidth(110.0);
        playTetris.setStyle("-fx-background-color: #185adb; -fx-cursor: hand;" );
        playTetris.setTextFill(Color.valueOf("#ffc947"));
        playTetris.setFont(new Font(23.0));
        playTetris.setOnAction(event -> {
            try {
                glc.playTetris(true);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        pane.getChildren().add(playTetris);

        playNormal=new Button("Play normal mode");
        playNormal.setId("play button");
        playNormal.setAlignment(Pos.BOTTOM_LEFT);
        playNormal.setLayoutX(300.0);
        playNormal.setLayoutY(204.0);
        playNormal.setMnemonicParsing(false);
        playNormal.prefHeight(35.0);
        playNormal.prefWidth(110.0);
        playNormal.setStyle("-fx-background-color: #185adb; -fx-cursor: hand;" );
        playNormal.setTextFill(Color.valueOf("#ffc947"));
        playNormal.setFont(new Font(23.0));
        playNormal.setOnAction(event -> {
            glc.playNormal();
        });
        pane.getChildren().add(playNormal);


        lastGame=new Button("Play last game saved");
        lastGame.setLayoutX(178.0);
        lastGame.setLayoutY(291.0);
        lastGame.setOnAction(event -> {
            gameControler.playLastGameRecorded();;
        });
        pane.getChildren().add(lastGame);

        File newFile = new File("lastGame.txt");
        if (newFile.length() == 0) {
            lastGame.setVisible(false);
        } else  lastGame.setVisible(true);
        this.getChildren().add(pane);
    }

    public void setControler(gameLaucherController gl){
        this.glc=gl;
    }
}
