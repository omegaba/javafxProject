package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Scene;

public class gameLaucherController {
    private gameLauncher gl;

    public gameLaucherController(gameLauncher g) {
        this.gl = g;
    }

    public void setGame(gameLauncher g) {
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

    public void playLastGameRecorded() {
        ArrayList<String> datalist = new ArrayList<>();
        File f = new File("lastGame.txt");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while (line != null) {
                datalist.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        game g = new game(null);
        double t = Double.parseDouble(datalist.get(0));
        double d = Double.parseDouble(datalist.get(1));
        boolean tet = Boolean.valueOf(datalist.get(2));
        boolean pww = Boolean.valueOf(datalist.get(3));
        int nb = Integer.parseInt(datalist.get(4));
        boolean mult = Boolean.valueOf(datalist.get(5));
        gameControler gc = new gameControler(g, t, d, tet, pww, nb, mult, null);
        g.setControler(gc);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);
    }

}
