package launchPattern;

import Register.ProtocoleRegister;
import login.ProtocoleLogin;
import servPattern.IContext;
import servPattern.IProtocole;

import java.io.*;

public class MonGrosProtocole implements IProtocole {
    IProtocole ProtocoleCourant;
    public void execute(IContext c, InputStream unInput, OutputStream unOutput) {
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                unInput));
        PrintStream os = new PrintStream(unOutput);
        try {
            String valeurExpediee = "";
            if ((inputReq = is.readLine()) != null) {
                System.out.println("Ordre Recu " + inputReq);
                if (inputReq.equals("register")){
                    ProtocoleCourant =  new ProtocoleRegister();
                }
                if (inputReq.equals("login")){
                    ProtocoleCourant = new ProtocoleLogin();
                }
//                if (inputReq.equals("chat")){
//                    ProtocoleCourant = new ProtocoleChat();
//                }
//                if (inputReq.equals("invitation")){
//                    ProtocoleCourant = new ProtocoleInvitation();
//                }
                System.out.println("Protocole " + inputReq + "lancé");
                ProtocoleCourant.executebis(c, is, os);
                System.out.println("Protocole " + inputReq + "fini");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void executebis(IContext aContext, BufferedReader is, PrintStream os) {

    }
}
