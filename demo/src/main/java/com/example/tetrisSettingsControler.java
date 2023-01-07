package com.example;

import java.io.IOException;

import javafx.scene.Scene;

public class tetrisSettingsControler {

    private tetrisSettingsLauncher laucher;

    public tetrisSettingsControler(tetrisSettingsLauncher t) {
        this.laucher = t;
    }

    public void playSettings(boolean solo) {

        if (solo == true) {
            game g = new game(null);
            gameControler gc = gameControler.builder().game(g).timer(120).difficulte(3).tetris(true).playWwords(false)
                    .nbwords(0).multi(false).clientOrHost(null).build();

            g.setControler(gc);
            Scene s = new Scene(g, 600, 450);
            App.changeScene(s);
        } else {
            hostJoinPanel hj = new hostJoinPanel(null);
            hostJoinControler hjc = new hostJoinControler(hj);
            hj.setControler(hjc);
            Scene s2 = new Scene(hj, 600, 450);
            App.changeScene(s2);
        }
    }
}
