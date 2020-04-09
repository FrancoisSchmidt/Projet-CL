package groups;

import java.io.PrintStream;

public class ClientGestionGroup {

    private PrintStream socOut;

    private String auteur;

    public ClientGestionGroup(PrintStream so, String Auteur){
        socOut = so;
        auteur = Auteur;
    }
    public void createGroup(String grpName) {
        try {
            System.out.println(auteur + " : creation du groupe " + grpName);
            this.socOut.println(auteur);
            this.socOut.println(grpName);
            this.socOut.flush();
            System.out.println("Envoyé !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void acceptInvite(String grpName){
        try {
            System.out.println(auteur + " : accepted invitation to group " + grpName);
            this.socOut.println(auteur);
            this.socOut.println(grpName);
            this.socOut.flush();
            System.out.println("Envoyé !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inviteToGroup(String destinataire, String grpName){
        try {
            System.out.println(auteur + " : invited " + destinataire +" to group " + grpName);
            this.socOut.println(auteur);
            this.socOut.println(grpName);
            this.socOut.println(destinataire);
            this.socOut.flush();
            System.out.println("Envoyé !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void quitGroup(String grpName){
        try {
            System.out.println(auteur + " : left group " + grpName);
            this.socOut.println(auteur);
            this.socOut.println(grpName);
            this.socOut.flush();
            System.out.println("Envoyé !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
