package com.example;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class joinControler {
    private joinPanel laucher;

    public joinControler(joinPanel h) {
        this.laucher = h;
    }

    public void play(KeyEvent ke) {

        if (ke.getCode().equals(KeyCode.SPACE)) {
            String str = laucher.adress.getText();
            try {
                Client c = new Client(str);
                // game g = new game(null);
                // gameControler gc = new gameControler(g, 120, 0, true, false, true,c);
                // g.setControler(gc);
                // Scene s = new Scene(g, 600, 450);
                // App.changeScene(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
