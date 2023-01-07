package com.example;

import java.net.*;
import java.util.concurrent.ExecutionException;
import javafx.scene.Scene;
import java.io.*;

public class Client {
    public static int port = 13000;
    public static BufferedReader con_br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader sock_br;
    PrintWriter sock_pw;
    Socket sock;
    public gameControler gameCtrl;

    public Client(String adr) throws IOException, InterruptedException, ExecutionException {
        System.out.print("Enter server address: ");
        String address = adr;
        sock = new Socket(address, port);

        sock_br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        sock_pw = new PrintWriter(sock.getOutputStream(), true);
        System.out.println("Connection established");

        game g = new game(null);
        gameCtrl = new gameControler(g, 120, 0, true, false,0, true, this);
        g.setControler(gameCtrl);
        Scene s = new Scene(g, 600, 450);
        App.changeScene(s);

        Thread chat_client_reader = new Reader("chat_client_reader", sock_pw, sock_br, this);
        chat_client_reader.start();
    }
}