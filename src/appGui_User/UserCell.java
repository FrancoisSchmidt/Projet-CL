package appGui_User;

import context.User;
import groups.ClientGestionGroup;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.beans.value.ChangeListener;

public class UserCell extends ListCell<User> {
    @Override
    public void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        if (user == null || empty) {
            setGraphic(null);
        }
        else {
            setGraphic(this.createCell(user));
        }
    }

    public Node createCell(User user) {
        StackPane mainPane = new StackPane();
        VBox VBoxMain = new VBox();
        HBox HBoxMain = new HBox();

        //Name label
        Label nameLabel = new Label(user.getName());
        //Quick chat button
        Button butMessage = new Button ("");
        butMessage.setPrefSize(20,20);
        butMessage.getStyleClass().add("butMessage");
        butMessage.setTooltip(new Tooltip("Send Message"));
        butMessage.setVisible(false);
        //Invite to group button
        Button butInvite = new Button("");
        butInvite.setPrefSize(20,20);
        butInvite.getStyleClass().add("butInvite");
        butInvite.setTooltip(new Tooltip("Invite User to Group"));
        butInvite.setVisible(false);
        this.hoverProperty().addListener((observable, oldValue, newValue) -> {
            String currentTab = ((UserView) this.getListView()).root.chatPane.getSelectionModel().getSelectedItem().getText();
            if (newValue && currentTab.substring(0, 1).equals("#")) {
                butInvite.setTooltip(new Tooltip("Invite User to "+currentTab));
                butInvite.setVisible(true);}
            else {butInvite.setVisible(false);
                butInvite.setTooltip(null);}
            if (newValue) {
                butMessage.setVisible(true);
                butMessage.setTooltip(new Tooltip("Send Message"));}
            else {butMessage.setVisible(false);
                butMessage.setTooltip(null);}
        });
        butMessage.setOnAction(actionevent -> {
            ((UserView) this.getListView()).root.chatPane.openNewTab(user.getName());
        });
        butInvite.setOnAction(actionevent -> {
            String grpName = ((UserView) this.getListView()).root.chatPane.getSelectionModel().getSelectedItem().getText();
            String userInvited = user.getName();
            ((UserView) this.getListView()).root.monGrosClient.transmettreOrdre("inviteToGroup");
            ((UserView) this.getListView()).root.clientGestionGroup.inviteToGroup(userInvited,grpName);
        });

        /* set custom css if user is me */
        if (((UserView) this.getListView()).me.equals(user.getName())) {this.getStyleClass().add("me-cell");}
        else {this.getStyleClass().remove("me-cell");}

        VBoxMain.getChildren().addAll(nameLabel);
        VBoxMain.setSpacing(1);
        HBoxMain.getChildren().addAll(butInvite,butMessage,VBoxMain);
        HBoxMain.setAlignment(Pos.CENTER_LEFT);
        HBoxMain.setSpacing(10);
        mainPane.getChildren().add(HBoxMain);
        return mainPane;
    }
}
