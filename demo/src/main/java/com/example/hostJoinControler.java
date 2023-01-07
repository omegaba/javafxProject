package com.example;


import javafx.scene.Scene;

public class hostJoinControler {
    private hostJoinPanel laucher;

    public hostJoinControler(hostJoinPanel h) {
        this.laucher = h;
    }

/*Cette méthode crée, si host vaut vrai, l'objet serveur 
 * autrement elle affiche le panel pour joindre une partie
 */
    public void playSettings(boolean host) {

        if (host) {
            try {
                Server serv = new Server();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            joinPanel j = new joinPanel(null);
            joinControler jc = new joinControler(j);
            j.setControler(jc);
            Scene s2 = new Scene(j, 600, 450);
            App.changeScene(s2);
        }
    }
}
