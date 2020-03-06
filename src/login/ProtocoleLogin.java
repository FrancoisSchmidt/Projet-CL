package login;

import servPattern.IContext;
import servPattern.IProtocole;

import java.io.*;

public class ProtocoleLogin implements IProtocole {

    private ToolsIds Ids = new ToolsIds();
    public void execute(IContext c , InputStream unInput , OutputStream unOutput ) {

        String inputReq;
        BufferedReader is = new BufferedReader(new InputStreamReader(
                unInput));
        PrintStream os = new PrintStream(unOutput);
        try {
            String valeurExpediee = "";

            if ((inputReq = is.readLine()) != null) {
                System.out.println("Ordre Recu " + inputReq);
                String nom = inputReq;
                String mdp = is.readLine();
                System.out.println(nom + mdp);
                if (Ids.checkMDP(nom, mdp)) {
                    valeurExpediee = "true";
                    System.out.println(valeurExpediee);
                }
                else{
                    valeurExpediee = "false";
                    System.out.println(valeurExpediee);
                }
                os.println(valeurExpediee);
            }
        } catch ( Exception e) {
            System.out.println(" Pb d'exception ");
        }
    }
}
