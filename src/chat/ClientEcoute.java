package chat;

import appGui.MainUI;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientEcoute extends Thread  {
    private BufferedReader socIn;
    private MainUI ui;

    public ClientEcoute(MainUI UI , BufferedReader si){
        this.socIn = si;
        this.ui = UI;
        this.start();
    }


    public void run(){

        while(true) {
            try {
                String fromUser;
                if ((fromUser=this.socIn.readLine()) != null) {
                    if (fromUser.equals("%UserList")) {
                        String message = this.socIn.readLine();
                        System.out.print(message);
                        String[] connectedUserList = message.split("\t");
                        this.updateConnectedUserList(connectedUserList);
                    }
                    else if (fromUser.equals("%invite")) {
                        String grpName = this.socIn.readLine();
                        String author = this.socIn.readLine();
                        System.out.print(author + " invited you to join group : "+ grpName);
                        this.inviteToGroupChat(grpName, author);
                    }
                    else {
                        String groupOrUserName = this.socIn.readLine();
                        String text = this.socIn.readLine();
                        this.afficherMessage(fromUser, groupOrUserName, text);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateConnectedUserList(String[] connectedUserList){
        ui.userList.update(connectedUserList);
        // La tu écris ton code pour update la liste des utilisateurs connectés dans ton UI
    }
    public void afficherMessage(String fromUser, String destinataire, String message){
        ui.chatPane.writeMessage(fromUser, destinataire, message);
        // là tu écris ta méthode pour afficher le "message" envoyé par "fromUser" dans l'onglet "destinataire",
        // que ça soit un group ou un utilisateur, j'avoue qu'on fait pas la diff déso :/
        // (à part qu'il faudra recevoir une invit pour entre dans un group)
    }

    public void inviteToGroupChat(String grpName, String author){
        //L'auteur est celui qui t'invite, grpName le nom du groupe
    }
}
