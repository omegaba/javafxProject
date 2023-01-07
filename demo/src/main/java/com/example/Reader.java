package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Reader extends Thread

{
    BufferedReader con_br;
    PrintWriter sock_pw;
    Object clientOrServer;
    gameControler gameCtrl;

    public Reader(String name, PrintWriter sock_pw, BufferedReader con_br, Object clientOrServer) {
        super(name);
        this.con_br = con_br;
        this.sock_pw = sock_pw;
        this.clientOrServer = clientOrServer;
        if (clientOrServer instanceof Client)
            this.gameCtrl = ((Client) clientOrServer).gameCtrl;
        else
            this.gameCtrl = ((Server) clientOrServer).gameCtrl;
    }

    public void actionRedWord() {
        if (gameCtrl.getTampon().size() == 15) {
            gameCtrl.validation();
        } else {
            gameCtrl.addToTampon();
            gameCtrl.getStringBuilder().setLength(0);
            for (int i = 1; i < gameCtrl.getTampon().size(); i++) {
                gameCtrl.getStringBuilder().append(gameCtrl.getTampon().get(i) + " ");
            }
            gameCtrl.getGame().sp.setText(gameCtrl.getStringBuilder().toString());
            gameCtrl.setTimer(Math.round((5 * Math.pow(0.9, gameCtrl.getDifficulty())) * 100.0) / 100.0);
        }
    }

    public void run() {
        String str;
        try {
            while ((str = con_br.readLine()) != null) {;
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