package com.example;

import javafx.scene.Scene;

public class Tests {

    public static void run(String mode) {
        game g = new game(null);
        gameControler gc = null;
        switch (mode) {
            case "tetris":
                gc = gameControler.builder().game(g).timer(120).difficulte(3).tetris(true).playWwords(false)
                        .nbwords(0).multi(false).clientOrHost(null).build();
                break;
            case "temps":
                gc = gameControler.builder().game(g).timer(120).difficulte(0).tetris(false).playWwords(false)
                        .nbwords(0).multi(false).clientOrHost(null).build();
                break;
            case "nbWords":
                gc = gameControler.builder().game(g).timer(120).difficulte(0).tetris(false).playWwords(true)
                        .nbwords(15).multi(false).clientOrHost(null).build();
                break;
        }

        g.setControler(gc);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);
    }

}
