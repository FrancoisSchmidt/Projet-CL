package groups;

import servPattern.IContext;
import servPattern.IProtocole;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class ProtocoleAcceptInvite implements IProtocole {
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

    }

    public void executebis(IContext LeContext, BufferedReader is, PrintStream os) {
        String auteur;
        try{
            if ((auteur=is.readLine()) != null) {
                String grpName = is.readLine();
                System.out.println(auteur + " joined group " + grpName);
                LeContext.sendMessageToGroup("[Serveur]", grpName, auteur+" joined the group after being invited");
                LeContext.addUserToGroupChat(auteur, grpName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return null;
    }
}
