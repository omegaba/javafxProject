package com.example;

import java.net.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javafx.scene.Scene;

import java.io.*;

public class Client {
    public static int port = 13000;
    public static BufferedReader con_br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader sock_br;
    public gameControler gameCtrl;

    public Client(String adr) throws IOException, InterruptedException, ExecutionException {
        System.out.print("Enter server address: ");
        String address = adr;
        Socket sock = new Socket(address, port);

        sock_br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintWriter sock_pw = new PrintWriter(sock.getOutputStream(), true);
        System.out.println("Connection established");

        game g = new game(null);
        gameCtrl = new gameControler(g, 120, 0, true, false, true, this);
        g.setControler(gameCtrl);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);

        Thread chat_client_writer = new Writer("chat_client_writer", sock_pw, con_br);
        chat_client_writer.start();

        Thread chat_client_reader = new Reader("chat_client_reader", sock_pw, sock_br,this);
        chat_client_reader.start();
        // utiliser completable future pour pouvoir faire le run du serveur et afficher
        // le jeu en même temps
        // Executor executor = Executors.newFixedThreadPool(10);
        // CompletableFuture<Void> future = CompletableFuture.runAsync(r,executor);
        // future.get();
        // future.join();
        // future.complete(null);

        // sock.close();
    }
    
    /*Runnable r = new Runnable() {
        @Override
        public void run() {
            String str;
            try {
                while ((str = sock_br.readLine()) != null) {
                    if (str.equals("rouge")) {
                        print_a();
                    }
                    System.out.println("\rserver: " + str);
                    System.out.print("> ");
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };*/
}