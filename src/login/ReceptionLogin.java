package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    private String nomServeur;

    public ReceptionLogin(int unNumeroPort, String unnomServeur) {
        numeroPort = unNumeroPort;
        nomServeur = unnomServeur;
    }

    public void run() {
        ServerSocket serverSocket = null;
        try {
            loginSocket = new Socket(nomServeur, numeroPort);
            socOut = new PrintStream(loginSocket.getOutputStream());
            socIn = new BufferedReader (
                    new InputStreamReader(loginSocket.getInputStream()));
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
            String nom = null;
            String mdp = null;
            nom = socIn.readLine();
            mdp = socIn.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
