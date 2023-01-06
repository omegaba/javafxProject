package com.example;

import javafx.event.ActionEvent;

import javafx.scene.Scene;

import java.io.FileWriter;
import java.io.IOException;

public class popupController {

    private gameLauncher gl;
    private popup pop;

    public popupController(gameLauncher g, popup p) {
        this.gl = g;
        this.pop = p;
    }

    public void setGameLauncher (gameLauncher g){
        this.gl=g;
    }

    public void setPopup( popup p){
        this.pop=p;
    }

    public void submit(ActionEvent ae, popup pop) throws IOException {
        String name = pop.tf.getText();
        FileWriter myObj = new FileWriter("username.txt");
        myObj.write(name);
        myObj.close();

        Scene s = new Scene(new gameLauncher(pop.g), 600, 450);
        App.changeScene(s);
    }
}
