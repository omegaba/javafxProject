package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Reader extends Thread

{
    private BufferedReader con_br;
    private PrintWriter sock_pw;
    private Object clientOrServer;
    private gameControler gameCtrl;

    public Reader(Builder builder) {
        super(builder.name);
        this.con_br = builder.con_br;
        this.sock_pw = builder.sock_pw;
        this.clientOrServer = builder.clientOrServer;
        if (clientOrServer instanceof Client)
            this.gameCtrl = ((Client) builder.clientOrServer).getControler();
        else
            this.gameCtrl = ((Server) builder.clientOrServer).getControler();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private PrintWriter sock_pw;
        private BufferedReader con_br;
        private Object clientOrServer;

        public Reader build() {
            return new Reader(this);
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder sock_pw(PrintWriter sock_pw) {
            this.sock_pw = sock_pw;
            return this;
        }

        public Builder con_br(BufferedReader con_br) {
            this.con_br = con_br;
            return this;
        }

        public Builder clientOrServer(Object clientOrServer) {
            this.clientOrServer = clientOrServer;
            return this;
        }
    }

    /*
     * Cette méthode s'occupe de forcer la avalidation
     * ou d'ajouter un mot dans le tampon lorsqu'on est en mode multi-joueur
     */
    public void actionRedWord() {
        if (gameCtrl.getTampon().size() == 15) {
            gameCtrl.validation();
        } else {
            gameCtrl.addToTampon();
            gameCtrl.getStringBuilder().setLength(0);
            for (int i = 1; i < gameCtrl.getTampon().size(); i++) {
                gameCtrl.getStringBuilder().append(gameCtrl.getTampon().get(i) + " ");
            }
            gameCtrl.getGame().getText().setText(gameCtrl.getStringBuilder().toString());
            gameCtrl.setTimer(Math.round((5 * Math.pow(0.9, gameCtrl.getDifficulty())) * 100.0) / 100.0);
        }
    }

    /*Cette méthode s'occupe de l'intérction entre le client et le serveur,
     * on va lire dans con_br
     * et exécuter des actions  en fonction de celui qui reçoit le message entre le client et le serveur 
    */

    public void run() {
        String str;
        try {
            while ((str = con_br.readLine()) != null) {
                ;

                if (clientOrServer instanceof Client) {
                    System.out.println("\rserver: " + str);
                    System.out.print("> ");
                    actionRedWord();
                } else {
                    System.out.println("\rclient: " + str);
                    System.out.print("> ");
                    actionRedWord();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}