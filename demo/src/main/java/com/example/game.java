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
    private  gameControler gamecontroler;
    private Text secondvalue;
    private Button menu;
    private Text userEntry;
    private Text text;
    private TextField userWord;
    private Text wordsPerMin;
    private Text accuracyvalue;
    private Text firstWordText;
    private Text vie;
    private Text vieValue;
    private Circle tetrisCircle;
    private Text second;
    private Button lastgame;
    public void setControler(gameControler g) {
        this.gamecontroler = g;
    }

    public game(gameControler gameC) {
        // setStyle("-fx-background-color: #0a1931;");
        this.gamecontroler = gameC;
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

        secondvalue = new Text();
        secondvalue.setLayoutX(174.0);
        secondvalue.setLayoutY(92.0);
        secondvalue.setStrokeType(StrokeType.OUTSIDE);
        secondvalue.setStrokeWidth(0.0);
        secondvalue.setTextAlignment(TextAlignment.CENTER);
        secondvalue.setWrappingWidth(42.64990234375);
        secondvalue.setFont(new Font(19.0));
        getChildren().add(secondvalue);

        second = new Text("");
        second.setLayoutX(166.0);
        second.setLayoutY(113.0);
        second.setStrokeType(StrokeType.OUTSIDE);
        second.setStrokeWidth(0.0);
        second.setFont(new Font(13.0));
        getChildren().add(second);

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

        Text vitesse = new Text("Speed");
        // wpm.setFill(Color.WHITE);
        vitesse.setLayoutX(272.0);
        vitesse.setLayoutY(112.0);
        vitesse.setStrokeType(StrokeType.OUTSIDE);
        vitesse.setStrokeWidth(0.0);
        vitesse.setFont(new Font(13.0));
        getChildren().add(vitesse);

        Text accuracy = new Text("% accuracy");
        accuracy.setLayoutX(362.0);
        accuracy.setLayoutY(112.0);
        accuracy.setStrokeType(StrokeType.OUTSIDE);
        accuracy.setStrokeWidth(0.0);
        accuracy.setFont(new Font(13.0));
        getChildren().add(accuracy);

        wordsPerMin = new Text();
        wordsPerMin.setLayoutX(279.0);
        wordsPerMin.setLayoutY(93.0);
        wordsPerMin.setStrokeType(StrokeType.OUTSIDE);
        wordsPerMin.setStrokeWidth(0.0);
        wordsPerMin.setTextAlignment(TextAlignment.CENTER);
        wordsPerMin.setWrappingWidth(42.64990234375);
        wordsPerMin.setFont(new Font(21.0));
        getChildren().add(wordsPerMin);

        accuracyvalue = new Text();
        accuracyvalue.setLayoutX(367.0);
        accuracyvalue.setLayoutY(93.0);
        accuracyvalue.setStrokeType(StrokeType.OUTSIDE);
        accuracyvalue.setStrokeWidth(0.0);
        accuracyvalue.setTextAlignment(TextAlignment.CENTER);
        accuracyvalue.setWrappingWidth(49.0);
        accuracyvalue.setFont(new Font(21.0));
        getChildren().add(accuracyvalue);

        userWord = new TextField();
        userWord.setAlignment(Pos.CENTER);
        userWord.setLayoutX(145.0);
        userWord.setLayoutY(222.0);
        userWord.setPrefHeight(42.0);
        userWord.setPrefWidth(214.0);
        userWord.setFont(new Font(20.0));
        userWord.setOnKeyPressed((event -> gamecontroler.startGame(event)));
        getChildren().add(userWord);

        userEntry = new Text("word");
        userEntry.setLayoutX(145.0);
        userEntry.setLayoutY(302.0);
        userEntry.setStrokeType(StrokeType.OUTSIDE);
        userEntry.setStrokeWidth(0.0);
        userEntry.setTextAlignment(TextAlignment.CENTER);
        userEntry.setWrappingWidth(184.74749755859375);
        userEntry.setFont(new Font(21.0));
        getChildren().add(userEntry);

        firstWordText = new Text();
        firstWordText.setLayoutX(145.0);
        firstWordText.setLayoutY(192.0);
        firstWordText.setStrokeType(StrokeType.OUTSIDE);
        firstWordText.setStrokeWidth(0.0);
        firstWordText.setTextAlignment(TextAlignment.CENTER);
        firstWordText.setWrappingWidth(184.74749755859375);
        firstWordText.setFont(new Font(21.0));
        getChildren().add(firstWordText);

        text = new Text("word");
        text.setFill(Color.valueOf("#0000005b"));
        text.setId("secondProgram");
        text.setLayoutX(362.0);
        text.setLayoutY(191.0);
        text.setStrokeType(StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setWrappingWidth(184.74749755859375);
        text.setFont(new Font(21.0));
        getChildren().add(text);

        this.menu = new Button("Menu");
        menu.setLayoutX(222.254655);
        menu.setLayoutY(351.0);
        menu.setMnemonicParsing(false);
        menu.setOnAction(event -> {
            try {
                gameControler.toMainMenu();;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        lastgame=new Button("Jouer a la dernère partie sauvegardée");
        getChildren().add(menu);
        lastgame.setLayoutX(135.254655);
        lastgame.setLayoutY(391.0);
        lastgame.setMnemonicParsing(false);
        lastgame.setOnAction(event -> {
            gamecontroler.playLastGameRecorded();
        });
        getChildren().add(lastgame);
    }

    public gameControler getGamecontroler() {
        return gamecontroler;
    }

    public Text getSecondvalue() {
        return secondvalue;
    }

    public Button getMenu() {
        return menu;
    }

    public Text getUserEntry() {
        return userEntry;
    }

    public Text getText() {
        return text;
    }

    public TextField getUserWord() {
        return userWord;
    }

    public Text getWordsPerMin() {
        return wordsPerMin;
    }

    public Text getAccuracyvalue() {
        return accuracyvalue;
    }

    public Text getFirstWordText() {
        return firstWordText;
    }

    public Text getVie() {
        return vie;
    }

    public Text getVieValue() {
        return vieValue;
    }

    public Circle getTetrisCircle() {
        return tetrisCircle;
    }

    public Text getSecond() {
        return second;
    }

    public Button getLastgame() {
        return lastgame;
    }
}
