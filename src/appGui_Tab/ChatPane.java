package appGui_Tab;
import javafx.application.Platform;
import javafx.scene.control.*;
import appGui.*;

public class ChatPane extends TabPane {
    public String me;
    public MainUI root;
    public ChatPane(MainUI root) {
        this.root = root;
        this.me = root.username;}
    public void writeMessage(String fromUser, String destinataire, String message) {
        Platform.runLater(()->{
            Boolean tabExist = false;
            for (Tab tab : this.getTabs()) {
                if (tab.getText().equals(destinataire)) {
                    /* Si message a un groupe ou message de moi vers destinataire */
                    tabExist = true;
                    ((GroupTab) tab).writeMessage(fromUser, message);
                }
                else if (tab.getText().equals(fromUser) && destinataire.equals(me)) {
                    /* Si message privee */
                    tabExist = true;
                    ((GroupTab) tab).writeMessage(fromUser, message);
                }
            }
            if (tabExist==false) {
                /* ouverture d'une nouvelle fenetre de chat */
                if (destinataire.equals(me)) {
                    /* fenetre privee */
                    GroupTab newTab = new GroupTab(this.root,fromUser,0);
                    this.getTabs().add(newTab);
                    newTab.writeMessage(fromUser,message);
                }
                else {
                    /* message a un groupe*/
                    GroupTab newTab = new GroupTab(this.root,destinataire,0);
                    this.getTabs().add(newTab);
                    newTab.writeMessage(fromUser,message);
                }
            }
        });
    }
}
