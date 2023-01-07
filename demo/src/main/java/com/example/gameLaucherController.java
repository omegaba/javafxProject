package com.example;

import java.io.IOException;

import javafx.scene.Scene;

public class gameLaucherController {
    private gameLauncher gl;

    public gameLaucherController(gameLauncher g) {
        this.gl = g;
    }

    public void playTetris(boolean tetris) throws IOException {
        tetrisSettingsLauncher t = new tetrisSettingsLauncher(null);
        tetrisSettingsControler tc = new tetrisSettingsControler(t);
        t.setControler(tc);
        Scene s = new Scene(t, 600, 450);
        App.changeScene(s);

    }

    public void playNormal() {
        normalModeSettingsLaucher n = new normalModeSettingsLaucher(null);
        normalModeControler nmc = new normalModeControler(n);
        n.setControler(nmc);
        Scene s = new Scene(n, 600, 450);
        App.changeScene(s);
    }

    public void setGame(gameLauncher g) {
        this.gl = g;
    }
}
