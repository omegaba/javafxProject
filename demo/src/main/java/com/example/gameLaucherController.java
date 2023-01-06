package com.example;

import java.io.File;
import java.io.IOException;
import javafx.scene.Scene;


public class gameLaucherController {
    private gameLauncher gl;

    public gameLaucherController(gameLauncher g) {
        this.gl = g;
    }

    public void setGame(gameLauncher g){
        this.gl=g;
    }

    public void playTetris(boolean tetris) throws IOException {
        File newFile = new File("username.txt");
        if (newFile.length() == 0) {
            // popupController pc =new popupController(gl, p);
            Scene s = new Scene(new popup(this), 600, 450);
            App.changeScene(s);
        } else {

            tetrisSettingsLauncher t = new tetrisSettingsLauncher(null);
            tetrisSettingsControler tc= new tetrisSettingsControler(t);
            t.setControler(tc);
            Scene s = new Scene(t, 600, 450);
            App.changeScene(s);
            /*game g = new game(null);
            gameControler gc = new gameControler(g, 0, 1, true, false);
            g.setControler(gc);
            Scene s = new Scene(g, 600, 450);
            App.changeScene(s);*/
          
        }
    }

    public void playNormal() {
        File newFile = new File("username.txt");
        if (newFile.length() == 0) {
            // popupController pc =new popupController(gl, p);
            Scene s = new Scene(new popup(this), 600, 450);
            App.changeScene(s);
        } else {
            normalModeSettingsLaucher n = new normalModeSettingsLaucher(null);
            normalModeControler nmc = new normalModeControler(n);
            n.setControler(nmc);
            Scene s = new Scene(n, 600, 450);
            App.changeScene(s);
        }
    }

}
