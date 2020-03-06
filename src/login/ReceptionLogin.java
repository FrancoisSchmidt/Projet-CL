package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class ReceptionLogin {

    private ProtocoleIds Ids = new  ProtocoleIds();

    private PrintStream socOut;

    private BufferedReader socIn;

    private Socket clientSocket;

    private int connexion = 0;

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
        int maxConnexions = 1;
        while (connexion < maxConnexions) {
            try {
                System.out.println("Attente du serveur pour la communication d'un client " );
                clientSocket = serverSocket.accept();
                connexion ++;
                System.out.println("connexion faite" );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println("création socket " );
            Socket loginSocket = new Socket("localhost", numeroPort);
            socOut = new PrintStream(loginSocket.getOutputStream());
            socIn = new BufferedReader (
                    new InputStreamReader(loginSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("try nom + mdp" );
            String verifs;
            boolean verif;
            String nom;
            String mdp;
            nom = socIn.readLine();
            System.out.println("nom + mdp reçu");
            mdp = socIn.readLine();
            System.out.println(nom + " " + mdp);
            verif = Ids.checkMDP(nom, mdp);
            verifs = Boolean.toString(verif);
            System.out.println(verifs);
            socOut.println( verifs );
            socOut.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            serverSocket.close();
            connexion --;
        } catch (IOException e) {
            System.out.println("Could not close");
        }
    }
}
