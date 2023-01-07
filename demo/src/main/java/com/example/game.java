package com.example;

import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class game extends AnchorPane {
    public gameControler gc;
    public Text nb1;
    public Button play;
    public Text pw;
    public Text sp;
    public TextField userWord;
    public Text wordsPerMin;
    public Text accuracy;
    public Text firstWordText;
    public Text vie;
    public Text vieValue;
    public Circle tetrisCircle;
    public Text scd;
    public Button lastgame;
    public void setControler(gameControler g) {
        this.gc = g;
    }

    public game(gameControler gameC) {
        // setStyle("-fx-background-color: #0a1931;");
        this.gc = gameC;
        this.setPrefHeight(400.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #87CEFA  ;");
        Circle c1 = new Circle();
        c1.setFill(Color.WHITE);
        c1.setLayoutX(195.0);
        c1.setLayoutY(94.0);
        c1.setRadius(50.0);
        c1.setStroke(Color.valueOf("#ffc947"));
        c1.setStrokeType(StrokeType.INSIDE);
        c1.setStrokeWidth(3.0);
        getChildren().add(c1);

        nb1 = new Text();
        nb1.setId("second");
        nb1.setLayoutX(174.0);
        nb1.setLayoutY(92.0);
        nb1.setStrokeType(StrokeType.OUTSIDE);
        nb1.setStrokeWidth(0.0);
        nb1.setTextAlignment(TextAlignment.CENTER);
        nb1.setWrappingWidth(42.64990234375);
        nb1.setFont(new Font(19.0));
        getChildren().add(nb1);

        scd = new Text("");
        scd.setLayoutX(166.0);
        scd.setLayoutY(113.0);
        scd.setStrokeType(StrokeType.OUTSIDE);
        scd.setStrokeWidth(0.0);
        scd.setFont(new Font(13.0));
        getChildren().add(scd);

        tetrisCircle = new Circle();
        tetrisCircle.setFill(Color.WHITE);
        tetrisCircle.setLayoutX(92.0);
        tetrisCircle.setLayoutY(94.0);
        tetrisCircle.setRadius(39.0);
        tetrisCircle.setStroke(Color.valueOf("#ffc947"));
        tetrisCircle.setStrokeType(StrokeType.INSIDE);
        tetrisCircle.setStrokeWidth(3.0);
        getChildren().add(tetrisCircle);

        vieValue = new Text();
        vieValue.setId("vieValue");
        vieValue.setLayoutX(70);
        vieValue.setLayoutY(92.0);
        vieValue.setStrokeType(StrokeType.OUTSIDE);
        vieValue.setStrokeWidth(0.0);
        vieValue.setTextAlignment(TextAlignment.CENTER);
        vieValue.setWrappingWidth(42.64990234375);
        vieValue.setFont(new Font(21.0));
        getChildren().add(vieValue);

        vie = new Text("Vies");
        vie.setLayoutX(80);
        vie.setLayoutY(112.0);
        vie.setStrokeType(StrokeType.OUTSIDE);
        vie.setStrokeWidth(0.0);
        vie.setFont(new Font(13.0));
        getChildren().add(vie);

        Text wpm = new Text("Vitesse");
        // wpm.setFill(Color.WHITE);
        wpm.setLayoutX(272.0);
        wpm.setLayoutY(112.0);
        wpm.setStrokeType(StrokeType.OUTSIDE);
        wpm.setStrokeWidth(0.0);
        wpm.setFont(new Font(13.0));
        getChildren().add(wpm);

        Text acc = new Text("% accuracy");
        // acc.setFill(Color.WHITE);
        acc.setLayoutX(362.0);
        acc.setLayoutY(112.0);
        acc.setStrokeType(StrokeType.OUTSIDE);
        acc.setStrokeWidth(0.0);
        acc.setFont(new Font(13.0));
        getChildren().add(acc);

        wordsPerMin = new Text();
        wordsPerMin.setId("wpm");
        wordsPerMin.setLayoutX(279.0);
        wordsPerMin.setLayoutY(93.0);
        wordsPerMin.setStrokeType(StrokeType.OUTSIDE);
        wordsPerMin.setStrokeWidth(0.0);
        wordsPerMin.setTextAlignment(TextAlignment.CENTER);
        wordsPerMin.setWrappingWidth(42.64990234375);
        wordsPerMin.setFont(new Font(21.0));
        getChildren().add(wordsPerMin);

        accuracy = new Text();
        accuracy.setId("accuracy");
        accuracy.setLayoutX(367.0);
        accuracy.setLayoutY(93.0);
        accuracy.setStrokeType(StrokeType.OUTSIDE);
        accuracy.setStrokeWidth(0.0);
        accuracy.setTextAlignment(TextAlignment.CENTER);
        accuracy.setWrappingWidth(49.0);
        accuracy.setFont(new Font(21.0));
        getChildren().add(accuracy);

        userWord = new TextField();
        userWord.setId("useWord");
        userWord.setAlignment(Pos.CENTER);
        userWord.setLayoutX(145.0);
        userWord.setLayoutY(222.0);
        userWord.setPrefHeight(42.0);
        userWord.setPrefWidth(214.0);
        userWord.setFont(new Font(20.0));
        userWord.setOnKeyPressed((event -> gc.startGame(event)));
        getChildren().add(userWord);

        pw = new Text("word");
        pw.setId("programWord");
        pw.setLayoutX(145.0);
        pw.setLayoutY(302.0);
        pw.setStrokeType(StrokeType.OUTSIDE);
        pw.setStrokeWidth(0.0);
        pw.setTextAlignment(TextAlignment.CENTER);
        pw.setWrappingWidth(184.74749755859375);
        pw.setFont(new Font(21.0));
        getChildren().add(pw);

        firstWordText = new Text();
        firstWordText.setId("programWord");
        firstWordText.setLayoutX(145.0);
        firstWordText.setLayoutY(192.0);
        firstWordText.setStrokeType(StrokeType.OUTSIDE);
        firstWordText.setStrokeWidth(0.0);
        firstWordText.setTextAlignment(TextAlignment.CENTER);
        firstWordText.setWrappingWidth(184.74749755859375);
        firstWordText.setFont(new Font(21.0));
        getChildren().add(firstWordText);

        sp = new Text("word");
        sp.setFill(Color.valueOf("#0000005b"));
        sp.setId("secondProgram");
        sp.setLayoutX(362.0);
        sp.setLayoutY(191.0);
        sp.setStrokeType(StrokeType.OUTSIDE);
        sp.setStrokeWidth(0.0);
        sp.setTextAlignment(TextAlignment.CENTER);
        sp.setWrappingWidth(184.74749755859375);
        sp.setFont(new Font(21.0));
        getChildren().add(sp);

        this.play = new Button("Play again");
        play.setId("playAgain");
        play.setLayoutX(222.254655);
        play.setLayoutY(351.0);
        play.setMnemonicParsing(false);
        play.setOnAction(event -> {
            try {
                gc.toMainMenu();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        lastgame=new Button("Jouer a la dernère partie sauvegardée");
        getChildren().add(play);
        lastgame.setLayoutX(135.254655);
        lastgame.setLayoutY(391.0);
        lastgame.setMnemonicParsing(false);
        lastgame.setOnAction(event -> {
            gc.playLastGameRecorded();
        });
        getChildren().add(lastgame);
    }
}
