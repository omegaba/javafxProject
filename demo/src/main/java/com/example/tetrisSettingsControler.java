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
            gameControler gc = new gameControler(g, 120, 0, true, false,0);
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

    public void toMainMenu() throws IOException {
        gameLaucherController glc = new gameLaucherController(null);
        gameLauncher g = new gameLauncher(null);
        g.setControler(glc);
        glc.setGame(g);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);
    }
}
