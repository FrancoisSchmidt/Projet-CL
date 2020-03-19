package Register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientRegister {
    private int numeroPort;

    private String nomServeur;

    private Socket socketServeur;

    private PrintStream socOut;

    private BufferedReader socIn;

    private String login;

    private String mdp;

    public boolean connecterAuServeur() {
        boolean ok = false;
        try {
            System.out.println("Tentative : " + nomServeur + " -- " + numeroPort);
            socketServeur = new Socket( nomServeur, numeroPort);
            socOut = new PrintStream(socketServeur.getOutputStream());
            socIn = new BufferedReader (
                    new InputStreamReader(socketServeur.getInputStream()));
            ok = true;
            System.out.println("Connexion faite");

        } catch (UnknownHostException e) {
            System.err.println("Serveur inconnu : " + e);

        } catch (ConnectException e) {
            System.err.println("Exception lors de la connexion:" + e);
            e.printStackTrace();

        } catch (IOException e) {
            System.err.println("Exception lors de l'echange de donnees:" + e);
        }
        return ok;
    }
    public void deconnecterDuServeur() {
        try {
            System.out.println("[ClientTCP] CLIENT : " + socketServeur);
            socOut.close();
            socIn.close();
            socketServeur.close();
        } catch (Exception e) {
            System.err.println("Exception lors de la deconnexion :  " + e);
        }
    }

    public ClientRegister(String unNomServeur, int unNumero, String log, String m) {
        numeroPort = unNumero;
        nomServeur = unNomServeur;
        login = log;
        mdp = m;

    }

    public String transmettreReg() {
        Scanner nom_mdp = new Scanner(System.in);
        String msgServeur = "false";
                try {
                    System.out.println("Requete client : " + this.login + ", " + this.mdp);
                    socOut.println(this.login);
                    socOut.println(this.mdp);
                    socOut.flush();
                    System.out.println("Envoyé !");
                    msgServeur = socIn.readLine();
                    System.out.println("Reponse serveur : " + msgServeur);
                    if (msgServeur.equals("false")) {
                        System.out.println("Le nom d'utilisateur est déjà utilisé");
                    }
                } catch (UnknownHostException e) {
                    System.err.println("Serveur inconnu : ");
                } catch (IOException e) {
                    System.err.println("Exception entree/sortie:  ");
//                    e.printStackTrace();
            }
            return msgServeur;
    }
}

