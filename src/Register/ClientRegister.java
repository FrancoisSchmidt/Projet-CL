package Register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
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


    public ClientRegister(String unNomServeur, int unNumero, String log, String m) {
        numeroPort = unNumero;
        nomServeur = unNomServeur;
        login = log;
        mdp = m;

    }

    public String transmettreLogin() {
        Scanner nom_mdp = new Scanner(System.in);
        String msgServeur = "false";
        while (!msgServeur.equals("true")) {
            try {
                System.out.println("Requete client : " + this.login + ", " + this.mdp);
                socOut.println(this.login);
                socOut.println(this.mdp);
                socOut.flush();
                System.out.println("Envoyé !");
                msgServeur = socIn.readLine();
                System.out.println("Reponse serveur : " + msgServeur);
                //deconnecterDuServeur();
                if (msgServeur.equals("false")) {
                    System.out.println("Le nom d'utilisateur est déjà utilisé");
                    this.login = nom_mdp.nextLine();
                    this.mdp = nom_mdp.nextLine();
                }
            } catch (UnknownHostException e) {
                System.err.println("Serveur inconnu : " + e);
            } catch (IOException e) {
                System.err.println("Exception entree/sortie:  " + e);
                e.printStackTrace();
            }
        }
        return msgServeur;
    }
}

