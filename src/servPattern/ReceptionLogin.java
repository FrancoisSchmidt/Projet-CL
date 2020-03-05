package servPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceptionLogin {
    private Socket loginSocket;
    private PrintStream socOut;
    private BufferedReader socIn;
    private int connexion = 0;
    private int maxConnexions = 1;
    private int numeroPort;

    public ReceptionLogin(int unNumeroPort) {
        numeroPort = unNumeroPort;
    }

    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(numeroPort);
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + numeroPort + ", " + e);
            System.exit(1);
        }
        while (connexion < maxConnexions) {
            try {
                loginSocket = serverSocket.accept();
                connexion ++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            socIn.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
