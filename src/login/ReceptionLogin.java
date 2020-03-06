package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;



public class ReceptionLogin {

    private ProtocoleIds Ids = new  ProtocoleIds();

    private PrintStream socOut;

    private BufferedReader socIn;

    private int connexion = 0;

    private int numeroPort;

    private String nomServeur;

    public ReceptionLogin(int unNumeroPort, String unnomServeur) {
        numeroPort = unNumeroPort;
        nomServeur = unnomServeur;
    }

    public void run() {
        ServerSocket serverSocket = null;
        Socket loginSocket;
        try {
            loginSocket = new Socket(nomServeur, numeroPort);
            socOut = new PrintStream(loginSocket.getOutputStream());
            socIn = new BufferedReader (
                    new InputStreamReader(loginSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + numeroPort + ", " + e);
            System.exit(1);
        }
        int maxConnexions = 1;
        while (connexion < maxConnexions) {
            try {
                System.out.println(" Attente du serveur pour la communication d'un client " );
                assert false;
                loginSocket = serverSocket.accept();
                connexion ++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            String verifs;
            boolean verif;
            String nom = null;
            String mdp = null;
            nom = socIn.readLine();
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
    }
}
