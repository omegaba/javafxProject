package com.example;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class joinControler {
    private joinPanel laucher;

    public joinControler(joinPanel h) {
        this.laucher = h;
    }

    /*Cette méthode crée l'objet client
     */
    public void play(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.SPACE)) {
            String str = laucher.getAdress().getText();
            try {
                Client c = new Client(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
