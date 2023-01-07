package com.example;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javafx.scene.Scene;

public class Server {
    public static int port = 13000;
    public static BufferedReader con_br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader csock_br;
    public gameControler gameCtrl;

    public Server() throws IOException, InterruptedException, ExecutionException {
        ServerSocket ssock = new ServerSocket(port);
        System.out.println("server: Waiting for client to connect");
        Socket csock = ssock.accept();
        System.out.println("server: Connection established");

        csock_br = new BufferedReader(new InputStreamReader(csock.getInputStream()));
        PrintWriter csock_pw = new PrintWriter(csock.getOutputStream(), true);

        game g = new game(null);
        gameCtrl = new gameControler(g, 120, 0, true, false, true, this);
        g.setControler(gameCtrl);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);

        Thread chat_server_writer = new Writer("chat_server_writer", csock_pw, con_br);
        chat_server_writer.start();

        Thread chat_server_Reader = new Reader("chat_server_writer", csock_pw, csock_br,this);
        chat_server_Reader.start();
        // utiliser completable future pour pouvoir faire le run du serveur et afficher
        // le jeu en même temps

        // Executor executor = Executors.newFixedThreadPool(10);
        // CompletableFuture<Void> future = CompletableFuture.runAsync(r,executor);
        // future.get();
        // future.complete(null);
        // future.join();

        // System.out.println("\rserver: Client has disconnected");
        // csock.close();
        // ssock.close();
    }

    /*Runnable r = new Runnable() {
        @Override
        public void run() {
            String str;
            try {
                while ((str = csock_br.readLine()) != null) {
                    if (str.equals("rouge")) {
                        print_a();
                    }
                    System.out.println("\rclient: " + str);
                    System.out.print("> ");
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };*/


}