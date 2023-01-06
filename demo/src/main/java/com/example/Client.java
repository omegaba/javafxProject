package com.example;

import java.net.*;
import java.util.concurrent.CompletableFuture;

import javafx.scene.Scene;

import java.io.*;

public class Client {
    public static int port = 13000;
    public static BufferedReader con_br = new BufferedReader(new InputStreamReader(System.in));

    static void print_a() {
        System.out.println("message reçu, ajout de mot");
    }

    public Client(String adr) throws IOException {
        System.out.print("Enter server address: ");
        String address = adr;
        Socket sock = new Socket(address, port);

        BufferedReader sock_br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintWriter sock_pw = new PrintWriter(sock.getOutputStream(), true);
        System.out.println("Connection established");
        
        game g = new game(null);
        gameControler gc = new gameControler(g, 120, 0, true, false, true, this);
        g.setControler(gc);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);

        Thread chat_client_writer = new Writer("chat_client_writer", sock_pw, con_br);
        chat_client_writer.start();
        // utiliser completable future pour pouvoir faire le run du serveur et afficher
        // le jeu en même temps

        String str;
        while ((str = sock_br.readLine()) != null) {
            if (str.equals("rouge")) {
                print_a();
            }
            System.out.println("\rserver: " + str);
            System.out.print("> ");
        }
        sock.close();
    }
}