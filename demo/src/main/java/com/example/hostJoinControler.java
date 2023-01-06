package com.example;

import java.io.IOException;

import javafx.scene.Scene;

public class hostJoinControler {
    private hostJoinPanel laucher;

    public hostJoinControler(hostJoinPanel h) {
        this.laucher = h;
    }

    public void playSettings(boolean host) {

        if (host) {
           
        }
        {
       
        }
    }

    public void toMainMenu() throws IOException {
        gameLaucherController glc = new gameLaucherController(null);
        gameLauncher g = new gameLauncher(null);
        g.setControler(glc);
        glc.setGame(g);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);
    }
}
