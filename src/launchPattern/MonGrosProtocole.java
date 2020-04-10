package launchPattern;

import Register.ProtocoleRegister;
import chat.ProtocoleChat;
import groups.ProtocoleAcceptInvite;
import groups.ProtocoleCreateGroup;
import groups.ProtocoleInviteToGroup;
import groups.ProtocoleQuitGroup;
import login.ProtocoleLogin;
import servPattern.IContext;
import servPattern.IProtocole;

import java.io.*;

public class MonGrosProtocole implements IProtocole {
    IProtocole ProtocoleCourant;
    private String name;

    public void execute(IContext c, InputStream unInput, OutputStream unOutput) {
        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                unInput));
        PrintStream os = new PrintStream(unOutput);

        while(true) {
            try {
                if ((inputReq = is.readLine()) != null) {
                    System.out.println("Ordre Recu " + inputReq);
                    if (inputReq.equals("register")) {
                        ProtocoleCourant = new ProtocoleRegister();
                        System.out.println("Protocole " + inputReq + "lancé");
                        ProtocoleCourant.executebis(c, is, os);
                        System.out.println("Protocole " + inputReq + "fini");
                    }
                    if (inputReq.equals("login")) {
                        ProtocoleCourant = new ProtocoleLogin();
                        System.out.println("Protocole " + inputReq + "lancé");
                        ProtocoleCourant.executebis(c, is, os);
                        this.name = this.ProtocoleCourant.getName();
                        System.out.println("Protocole " + inputReq + "fini");
                    }
                    if (inputReq.equals("chat")){
                        ProtocoleCourant = new ProtocoleChat();
                        System.out.println("Protocole " + inputReq + "lancé");
                        ProtocoleCourant.executebis(c, is, os);
                        System.out.println("Protocole " + inputReq + "fini");
                    }

                    if (inputReq.equals("inviteToGroup")){
                        ProtocoleCourant = new ProtocoleInviteToGroup();
                        System.out.println("Protocole " + inputReq + "lancé");
                        ProtocoleCourant.executebis(c, is, os);
                        System.out.println("Protocole " + inputReq + "fini");
                    }

                    if (inputReq.equals("createGroup")){
                        ProtocoleCourant = new ProtocoleCreateGroup();
                        System.out.println("Protocole " + inputReq + "lancé");
                        ProtocoleCourant.executebis(c, is, os);
                        System.out.println("Protocole " + inputReq + "fini");
                    }

                    if (inputReq.equals("quitGroup")){
                        ProtocoleCourant = new ProtocoleQuitGroup();
                        System.out.println("Protocole " + inputReq + "lancé");
                        ProtocoleCourant.executebis(c, is, os);
                        System.out.println("Protocole " + inputReq + "fini");
                    }

                    if (inputReq.equals("acceptInvite")){
                        ProtocoleCourant = new ProtocoleAcceptInvite();
                        System.out.println("Protocole " + inputReq + "lancé");
                        ProtocoleCourant.executebis(c, is, os);
                        System.out.println("Protocole " + inputReq + "fini");
                    }

                    if (inputReq.equals("loggout")){
                        if ((name = is.readLine()) != null){
                            c.removeConnectedUser(name);
                        }
                        break;
                    }

                }
            } catch (IOException ignored) {

            }
        }
    }


    public void executebis(IContext aContext, BufferedReader is, PrintStream os) {

    }
    public String getName(){
        return "";
    }
}
