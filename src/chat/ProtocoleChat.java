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
        String inputReq;
        try{
            String valeurExpediee="";
            if ((inputReq=is.readLine()) != null) {
                System.out.println("auteur :" + inputReq);
                String grp_cible = is.readLine();
                String msg = is.readLine();
                System.out.println(" message recu : " + msg);
                System.out.println(" destinataire : " + grp_cible);
                LeContext.sendMessageToAll(inputReq, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
