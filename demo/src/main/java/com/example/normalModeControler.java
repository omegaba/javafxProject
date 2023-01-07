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
        gameControler gc = new gameControler(g, 120, 0, false, playWwords,3,false,null);
        g.setControler(gc);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);
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
