package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

import javafx.scene.Scene;

public class Server {
    public static int port = 13000;
    public static BufferedReader con_br = new BufferedReader(new InputStreamReader(System.in));

    static void print_a() {
        System.out.println("message reçu, ajout de mot");
    }

    public Server() throws IOException {
        ServerSocket ssock = new ServerSocket(port);
        System.out.println("server: Waiting for client to connect");
        Socket csock = ssock.accept();
        System.out.println("server: Connection established");

        BufferedReader csock_br = new BufferedReader(new InputStreamReader(csock.getInputStream()));
        PrintWriter csock_pw = new PrintWriter(csock.getOutputStream(), true);

        game g = new game(null);
        gameControler gc = new gameControler(g, 120, 0, true, false, true, this);
        g.setControler(gc);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);

        Thread chat_server_writer = new Writer("chat_server_writer", csock_pw, con_br);
        chat_server_writer.start();
        // utiliser completable future pour pouvoir faire le run du serveur et afficher
        // le jeu en même temps

        String str;
        while ((str = csock_br.readLine()) != null) {
            if (str.equals("rouge")) {
                print_a();
            }
            System.out.println("\rclient: " + str);
            System.out.print("> ");
        }

        System.out.println("\rserver: Client has disconnected");
        csock.close();
        ssock.close();
    }
}