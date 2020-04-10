package appGui_Tab;
import javafx.application.Platform;
import javafx.scene.control.*;
import appGui.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ChatPane extends TabPane {
    public String me;
    public MainUI root;
    public GroupTab generalTab;
    public CreateTab createTab;

    public ChatPane(MainUI root) {
        this.root = root;
        this.me = root.username;

        //Opening main Tab
        this.generalTab = new GroupTab(root,"#General");
        this.getTabs().add(generalTab);
        HBox.setHgrow(this, Priority.ALWAYS);
        //AddNewTab Tab
        this.createTab = new CreateTab(this);
        this.getTabs().addAll(createTab);

    }
    public void writeMessage(String fromUser, String destinataire, String message) {
        Platform.runLater(()->{
            Boolean tabExist = false;
            for (Tab tab : this.getTabs()) {
                if (tab.getText().equals(destinataire) && !destinataire.equals(me)) {
                    /* Si message a un groupe ou message de moi vers destinataire */
                    tabExist = true;
                    ((GroupTab) tab).writeMessage(fromUser, message);
                    notifyNewM(tab);
                }
                else if (tab.getText().equals(fromUser) && destinataire.equals(me)) {
                    /* Si message privee */
                    tabExist = true;
                    ((GroupTab) tab).writeMessage(fromUser, message);
                    notifyNewM(tab);
                }
            }
            if (tabExist==false) {
                /* ouverture d'une nouvelle fenetre de chat */
                this.getTabs().remove(createTab);
                if (destinataire.equals(me)) {
                    /* fenetre privee */
                    GroupTab newTab = new GroupTab(this.root,fromUser);
                    this.getTabs().add(newTab);
                    newTab.writeMessage(fromUser,message);
                    notifyNewM(newTab);
                }
                else {
                    /* message a un groupe*/
                    GroupTab newTab = new GroupTab(this.root,destinataire);
                    this.getTabs().add(newTab);
                    newTab.writeMessage(fromUser,message);
                    notifyNewM(newTab);
                }
                this.getTabs().add(createTab);
            }
        });
    }
    public void notifyInvite(String grpInvite, String from) {
        Platform.runLater(()-> {
            boolean inviteExist = false;
            for (Tab tab : this.getTabs()) {
                if ((tab.getText().equals("[" + grpInvite + "]")) || (tab.getText().equals(grpInvite))) {
                    inviteExist = true;
                }
            }
            if (!inviteExist) {
                this.getTabs().remove(createTab);
                this.getTabs().add(new InviteTab(root, grpInvite, from));
                this.getTabs().add(createTab);
            }
        });
    }
    public void openNewTab(String tabName) {
        Platform.runLater(() -> {
            Boolean tabExist = false;
            for (Tab tab : this.getTabs()) {
                if (tab.getText().equals(tabName)) {
                    tabExist = true;
                    this.getSelectionModel().select(tab);
                }
            }
            if (!tabExist) {
                this.getTabs().remove(createTab);
                GroupTab newTab = new GroupTab(this.root,tabName);
                this.getTabs().add(newTab);
                this.getSelectionModel().select(newTab);
                this.getTabs().add(createTab);
            }
        });
    }

    public void notifyNewM(Tab tab) {
        tab.getStyleClass().add("notify");
    }
}
