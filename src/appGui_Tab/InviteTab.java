package appGui_Tab;

import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import launchPattern.*;
import appGui.*;
import javafx.scene.*;

public class InviteTab extends Tab{
    public MainUI root;
    public MonGrosClient monGrosClient;
    public String grpInvite;
    public String from;

    public InviteTab(MainUI root, String grpInvite, String from) {
        this.root = root;
        this.monGrosClient = root.monGrosClient;
        this.grpInvite = grpInvite;
        this.from = from;

        this.setText("["+grpInvite+"]");
        this.setClosable(false);
        this.getStyleClass().add("invitetab");

        this.setContent(createNode());
    }

    public Node createNode() {
        StackPane mainPane = new StackPane();
        VBox VBoxMain = new VBox();
        HBox HBoxLabel = new HBox();
        HBox HBoxButton = new HBox();

        Label labelFrom = new Label(from);
        labelFrom.getStyleClass().add("labelinvite1");
        Label labelInvite = new Label("Invated you to");
        labelInvite.getStyleClass().add("labelinvite2");
        Label labelGrp = new Label(grpInvite);
        labelGrp.getStyleClass().add("labelinvite1");

        Button butAccept = new Button("Accept");
        butAccept.getStyleClass().add("butinvite1");
        Button butDecline = new Button("Decline");
        butDecline.getStyleClass().add("butinvite2");
        butDecline.setOnAction(actionevent -> {
            this.getTabPane().getTabs().remove(this);
        });
        butAccept.setOnAction(actionevent -> {
            monGrosClient.transmettreOrdre("acceptInvite");
            root.clientGestionGroup.acceptInvite(grpInvite);
            ((ChatPane) this.getTabPane()).openNewTab(grpInvite);
            this.getTabPane().getTabs().remove(this);
        });

        HBoxLabel.getChildren().addAll(labelFrom,labelInvite,labelGrp);
        HBoxLabel.setSpacing(10);
        HBoxLabel.setAlignment(Pos.TOP_CENTER);
        HBoxButton.getChildren().addAll(butAccept,butDecline);
        HBoxButton.setSpacing(15);
        HBoxButton.setAlignment(Pos.TOP_CENTER);
        VBoxMain.getChildren().addAll(HBoxLabel,HBoxButton);
        VBoxMain.setSpacing(20);
        VBoxMain.setAlignment(Pos.CENTER);
        mainPane.getChildren().add(VBoxMain);
        return mainPane;
    }
}
