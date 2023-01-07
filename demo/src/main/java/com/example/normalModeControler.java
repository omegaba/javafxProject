package com.example;

import java.io.IOException;
import javafx.scene.Scene;

public class normalModeControler {
    private normalModeSettingsLaucher nml;

    public normalModeControler(normalModeSettingsLaucher n) {
        this.nml = n;
    }

    public void setMode(normalModeSettingsLaucher n) {
        this.nml = n;
    }

    public void playSettings(boolean playWwords) {
        game g = new game(null);
        gameControler gc = gameControler.builder().game(g).timer(120).difficulte(0).tetris(false).playWwords(playWwords)
                .nbwords(3).multi(false).clientOrHost(null).build();

        g.setControler(gc);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);
    }

}
