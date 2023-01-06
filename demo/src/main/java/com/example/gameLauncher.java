package com.example;
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class gameLauncher extends GridPane {

    private gameLaucherController glc;
    private Label displayWelcome;
    private Button playNormal;
    private Button playTetris;
    private Label data;
    private Circle circle1;
    private Circle circle2;
    private Circle circle3;
    private Text nbr, nbr2, nbr3, wpm, totalEntered,invalid;
    
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
        displayWelcome.setLayoutX(166.6);
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


        data=new Label("Overall data");
        data.setAlignment(Pos.CENTER);
        data.setLayoutX(178.0);
        data.setLayoutY(291.0);
        data.setPrefHeight(19.0);
        data.setPrefWidth(245.0);
        data.setTextAlignment(TextAlignment.CENTER);
        data.setTextFill(Color.WHITE);
        data.setFont(new Font(24.0));
        pane.getChildren().add(data);
        


        circle1=new Circle();
        circle1.setFill(Color.valueOf("#ffffff00"));
        circle1.setLayoutX(178.0);
        circle1.setLayoutY(378.0);
        circle1.setRadius(45.0);
        circle1.setStroke(Color.valueOf("#ffc947"));
        circle1.setStrokeType(StrokeType.INSIDE);
        circle1.setStrokeWidth(3.0);
        pane.getChildren().add(circle1);
        
        totalEntered= new Text("Total entered");
        totalEntered.setFill(Color.WHITE);
        totalEntered.setLayoutX(143.0);
        totalEntered.setLayoutY(398.0);
        totalEntered.setStrokeType(StrokeType.OUTSIDE);
        totalEntered.setStrokeWidth(0.0);
        totalEntered.setFont(new Font(13.0));
        pane.getChildren().add(totalEntered);
        
        nbr=new Text("0");
        nbr.setId("total");
        nbr.setFill(Color.WHITE);
        nbr.setLayoutX(148.0);
        nbr.setLayoutY(374.0);
        nbr.setStrokeType(StrokeType.OUTSIDE);
        nbr.setStrokeWidth(0.0);
        nbr.setTextAlignment(TextAlignment.CENTER);
        nbr.setWrappingWidth(60.30908203125);
        nbr.setFont(new Font(36.0));
        pane.getChildren().add(nbr);

        circle2=new Circle();
        circle2.setFill(Color.valueOf("#ffffff00"));
        circle2.setLayoutX(300.0);
        circle2.setLayoutY(378.0);
        circle2.setRadius(45.0);
        circle2.setStroke(Color.valueOf("#ffc947"));
        circle2.setStrokeType(StrokeType.INSIDE);
        circle2.setStrokeWidth(3.0);
        pane.getChildren().add(circle2);

        wpm= new Text("average wpm");
        wpm.setFill(Color.WHITE);
        wpm.setLayoutX(260.0);
        wpm.setLayoutY(398.0);
        wpm.setStrokeType(StrokeType.OUTSIDE);
        wpm.setStrokeWidth(0.0);
        wpm.setWrappingWidth(80.30908203125);
        wpm.setTextAlignment(TextAlignment.CENTER);
        wpm.setFont(new Font(13.0));
        pane.getChildren().add(wpm);


        nbr2= new Text("0");
        nbr2.setId("wpm");
        nbr2.setFill(Color.WHITE);
        nbr2.setLayoutX(270.0);
        nbr2.setLayoutY(374.0);
        nbr2.setStrokeType(StrokeType.OUTSIDE);
        nbr2.setStrokeWidth(0.0);
        nbr2.setTextAlignment(TextAlignment.CENTER);
        nbr2.setWrappingWidth(60.30908203125);
        nbr2.setFont(new Font(36.0));
        pane.getChildren().add(nbr2);

        circle3=new Circle();
        circle3.setFill(Color.valueOf("#ffffff00"));
        circle3.setLayoutX(423.0);
        circle3.setLayoutY(378.0);
        circle3.setRadius(45.0);
        circle3.setStroke(Color.valueOf("#ffc947"));
        circle3.setStrokeType(StrokeType.INSIDE);
        circle3.setStrokeWidth(3.0);
        pane.getChildren().add(circle3);

        invalid =new Text("Invalid");
        invalid.setFill(Color.WHITE);
        invalid.setLayoutX(406.0);
        invalid.setLayoutY(398.0);
        invalid.setStrokeType(StrokeType.OUTSIDE);
        invalid.setStrokeWidth(0.0);
        invalid.setFont(new Font(13.0));
        pane.getChildren().add(invalid);

        nbr3= new Text("0");
        nbr3.setId("invald");
        nbr3.setFill(Color.WHITE);
        nbr3.setLayoutX(393.0);
        nbr3.setLayoutY(374.0);
        nbr3.setStrokeType(StrokeType.OUTSIDE);
        nbr3.setStrokeWidth(0.0);
        nbr3.setTextAlignment(TextAlignment.CENTER);
        nbr3.setWrappingWidth(60.30908203125);
        nbr3.setFont(new Font(36.0));
        pane.getChildren().add(nbr3);


        this.getChildren().add(pane);
    }

    public void setControler(gameLaucherController gl){
        this.glc=gl;
    }
}
