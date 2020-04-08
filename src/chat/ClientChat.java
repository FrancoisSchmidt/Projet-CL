package chat;

import java.io.*;
import java.net.*;

public class ClientChat {

    private PrintStream socOut;

    private String auteur;

    public ClientChat(PrintStream so, String Auteur){
        socOut = so;
        auteur = Auteur;
    }
    public void transmettreMessage(String destinataire, String message) {
        try {
            System.out.println(auteur + " : envoie de " + message + " à " + destinataire);
            this.socOut.println(auteur);
            this.socOut.println(destinataire);
            this.socOut.println(message);
            this.socOut.flush();
            System.out.println("Envoyé !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//qui, pour qui, message