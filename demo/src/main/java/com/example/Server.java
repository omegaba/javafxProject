package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import javafx.scene.Scene;

public class Server {
    private  static int port = 13000;
    private static BufferedReader con_br = new BufferedReader(new InputStreamReader(System.in));
    private BufferedReader csock_br;
    private PrintWriter csock_pw;
    
    private ServerSocket ssock;
   
    private Socket csock;
    private gameControler gameCtrl;

    

    /*Le constructeur de la classe Serveur  s'occupe de créer le serveur
     * puis il attend qu'un utilisateur se connecte
     * et enfin une instance de jeu est créée pour le host
     */

    public Server() throws IOException, InterruptedException, ExecutionException {
        ssock = new ServerSocket(port);
        System.out.println("server: Waiting for client to connect");
        csock = ssock.accept();
        System.out.println("server: Connection established");

        csock_br = new BufferedReader(new InputStreamReader(csock.getInputStream()));
        csock_pw = new PrintWriter(csock.getOutputStream(), true);

        game g = new game(null);
        gameCtrl = gameControler.builder().game(g).timer(120).difficulte(0).tetris(true).playWwords(false)
                .nbwords(0).multi(true).clientOrHost(this).build();
        g.setControler(gameCtrl);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);

        Thread chat_server_Reader = Reader.builder().name("chat_server_writer").sock_pw(csock_pw).con_br(csock_br)
                .clientOrServer(this).build();
        chat_server_Reader.start();
    }

    public gameControler getControler(){
        return gameCtrl;
    }

    public Socket getCsock(){
        return csock;
    }

    public PrintWriter getCsock_pw() {
        return csock_pw;
    }

    public ServerSocket getSsock() {
        return ssock;
    }


}