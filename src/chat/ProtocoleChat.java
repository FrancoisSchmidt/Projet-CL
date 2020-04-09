package chat;

import servPattern.IContext;
import servPattern.IProtocole;
import context.LeContext;
import java.io.*;

import login.ToolsIds;
import servPattern.IProtocole;

public class ProtocoleChat implements IProtocole {

    private ToolsIds Ids = new ToolsIds();
    private String name = "";

    public void execute(IContext c , InputStream unInput , OutputStream unOutput ){}
    public String getName(){return "";}
    public void executebis(IContext LeContext, BufferedReader is, PrintStream os){
        String auteur;
        try{
            String valeurExpediee="";
            if ((auteur=is.readLine()) != null) {
                System.out.println("auteur :" + auteur);
                String grp_cible = is.readLine();
                String msg = is.readLine();
                System.out.println(" message recu : " + msg);
                System.out.println(" destinataire : " + grp_cible);
                if (LeContext.getConnectedUserList().contains(grp_cible)){
                    // le destinataire est un utilisateur connecté
                    LeContext.sendMessageToUser(auteur, grp_cible, msg);
                }
                else if (LeContext.getGroupList().contains(grp_cible)){
                    LeContext.sendMessageToGroup(auteur, grp_cible, msg);
                }
                else if (grp_cible.equals("#General")){
                    LeContext.sendMessageToAll(auteur, msg);
                }
                else{
                    LeContext.sendMessageToUser(auteur, grp_cible, "Message not sent because "+ grp_cible +" is not connected.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
