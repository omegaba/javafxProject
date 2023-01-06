package com.example;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Writer extends Thread

// Reads input from the console and sends it on the socket
// This thread needs to be killed when the socket closes
// Both the server and the client will use this class
{
    BufferedReader con_br;
    PrintWriter sock_pw;

    public Writer(String name, PrintWriter sock_pw, BufferedReader con_br) {
        super(name);
        this.sock_pw = sock_pw;
        this.con_br = con_br;
    }

    public void run() {
        String s;
        try {
            while (true) {
                System.out.print("> ");
                s = con_br.readLine();
                if (s != null)
                    sock_pw.println(s);
                else
                    break;
            }
        } catch (Exception e) {
            System.err.println("chat_server_writer: Exception occured:\n" + e);
        }
    }
}