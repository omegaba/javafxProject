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
    public static int port = 13000;
    public static BufferedReader con_br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader csock_br;
    PrintWriter csock_pw;
    ServerSocket ssock;
    Socket csock;
    public gameControler gameCtrl;

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
}