package groups;

import com.sun.management.GarbageCollectionNotificationInfo;
import servPattern.IContext;
import servPattern.IProtocole;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class ProtocoleQuitGroup implements IProtocole {
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

    }

    public void executebis(IContext LeContext, BufferedReader is, PrintStream os) {
        String auteur;
        try{
            String valeurExpediee="";
            if ((auteur=is.readLine()) != null) {
                String grpName = is.readLine();
                System.out.println(auteur + " left group : " + grpName);
                LeContext.sendMessageToGroup("[Serveur]", grpName, auteur+" left the group");
                LeContext.removeUserOfGroupChat(auteur, grpName);
                if (LeContext.getGroupMemberList(grpName).isEmpty()){
                    LeContext.deleteGroupChat(grpName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return null;
    }
}
