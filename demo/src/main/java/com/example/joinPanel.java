package com.example;

import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class joinPanel extends GridPane {
    private TextField adress;
    private joinControler controler;
    private Button play;

    public void setControler(joinControler jc) {
        this.controler = jc;
    }

    public joinPanel(joinControler c) {
        this.controler = c;
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #0a1931;");
        pane.setPrefHeight(450.0);
        pane.setPrefWidth(600.0);

        adress = new TextField();
        adress.setId("useWord");
        adress.setAlignment(Pos.CENTER);
        adress.setLayoutX(145.0);
        adress.setLayoutY(222.0);
        adress.setPrefHeight(42.0);
        adress.setPrefWidth(214.0);
        adress.setFont(new Font(20.0));
        adress.setOnKeyPressed((event -> controler.play(event)));
        pane.getChildren().add(adress);

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

    public TextField getAdress(){
        return adress;
    }
}
