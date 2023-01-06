package com.example;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.StrokeType;

import java.io.IOException;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class popup extends AnchorPane {
    private gameLauncher gl;
    private popupController pc;
    public TextField tf;
    public gameLaucherController g;

    public void setGameLauncher(gameLauncher g){
        this.gl=g;
    }

    public void setPopupControler(popupController p){
        this.pc=p;
    }

    public popup(gameLaucherController glc) {
        this.g=glc;
        prefHeight(400.0);
        prefWidth(600.0);
        Text t = new Text("Looks like this is your first time using Type Practice");
        t.setLayoutX(143.0);
        t.setLayoutY(95.0);
        t.setTextAlignment(TextAlignment.CENTER);
        t.setWrappingWidth(312.1329345703125);
        t.setFont(new Font(25.0));
        t.setStrokeType(StrokeType.OUTSIDE);
        t.setStrokeWidth(0.0);
        getChildren().add(t);
        tf=new TextField();
        tf.setPromptText("Name");
        tf.setId("username");
        tf.setLayoutX(225.0);
        tf.setLayoutY(174.0);
        getChildren().add(tf);
        Button b = new Button("Submit");
        b.setLayoutX(273.0);
        b.setLayoutY(220.0);
        b.setMnemonicParsing(false);
        b.setOnAction(event -> {
            try {
                pc= new popupController(gl, this);
                pc.submit(event, this);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        getChildren().add(b);
    }

}
