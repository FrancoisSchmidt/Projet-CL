package groups;

import servPattern.IContext;
import servPattern.IProtocole;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class ProtocoleCreateGroup implements IProtocole {
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

    }

    public void executebis(IContext LeContext, BufferedReader is, PrintStream os) {
        String auteur;
        try{
            if ((auteur=is.readLine()) != null) {
                String grpName = is.readLine();
                if (!grpName.equals("#General")){
                    if (LeContext.getGroupList().contains(grpName)&&!LeContext.getGroupMemberList(grpName).contains(auteur)){
                        LeContext.addUserToGroupChat(auteur, grpName);
                        LeContext.sendMessageToGroup("[Serveur]", grpName, auteur+" joined the group by itself");
                    }
                    else if (!LeContext.getGroupList().contains(grpName)){
                        System.out.println(auteur + " created group : " + grpName);
                        LeContext.createGroupChat(grpName);
                        LeContext.addUserToGroupChat(auteur, grpName);
                        LeContext.sendMessageToGroup("[Serveur]", grpName, auteur + " just created the group " + grpName);
                    }
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
