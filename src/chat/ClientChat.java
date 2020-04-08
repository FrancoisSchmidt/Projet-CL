package chat;

import java.io.*;
import java.net.*;

public class ClientChat {
    private int numeroPort;

    private String nomServeur;

    private Socket socketServeur;

    private PrintStream socOut;

    private BufferedReader socIn;

    private String message;

    private String groupe_cible;

    public ClientChat(String unNomServeur, int unNumero, String msg, String user){
        numeroPort = unNumero;
        nomServeur = unNomServeur;
        message = msg;
        groupe_cible = user;
    }

    public String transmettreMessage(PrintStream socOut, BufferedReader socIn) {
        String msgServeur = null;
        try {
            System.out.println("requete client : envoie de " + this.message + " à " + this.groupe_cible);
            socOut.println(this.groupe_cible);
            socOut.println(this.message);
            socOut.flush();
            System.out.println("Envoyé !");
            msgServeur = socIn.readLine();
            System.out.println("Reponse serveur : " + msgServeur);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgServeur;
    }
}

//qui, pour qui, message