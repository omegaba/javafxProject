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
            try {
                Server serv = new Server();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            joinPanel j = new joinPanel(null);
            joinControler jc = new joinControler(j);
            j.setControler(jc);
            Scene s2 = new Scene(j, 600, 450);
            App.changeScene(s2);
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
