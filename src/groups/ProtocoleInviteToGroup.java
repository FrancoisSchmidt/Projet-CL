package groups;

import servPattern.IContext;
import servPattern.IProtocole;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class ProtocoleInviteToGroup implements IProtocole {
    public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) {

    }

    public void executebis(IContext LeContext, BufferedReader is, PrintStream os) {
        String auteur;
        try{
            if ((auteur=is.readLine()) != null) {
                String grpName = is.readLine();
                String invited = is.readLine();
                System.out.println(auteur + " invited "+ invited +" to group " + grpName);
                LeContext.inviteToGroup(auteur, grpName, invited);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return null;
    }
}
