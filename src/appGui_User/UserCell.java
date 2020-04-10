package appGui_User;

import context.User;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.beans.value.ChangeListener;

public class UserCell extends ListCell<User> {
    @Override
    public void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        String name = null;
        if (user == null || empty) {
            this.setText(name);
            setGraphic(null);
        }
        else {
            name = user.getName();
            this.setText(name);
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
        butMessage.setVisible(false);
        this.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {butMessage.setVisible(true);}
            else {butMessage.setVisible(false);}
        });
        butMessage.setOnAction(actionevent -> {
            ((UserView) this.getListView()).root.chatPane.openNewTab(user.getName());
        });

        //TODO - remove this button ?
        //Button butTest = new Button("test");

        /* set custom css if user is me */
        if (((UserView) this.getListView()).me.equals(user.getName())) {this.getStyleClass().add("me-cell");}
        else {this.getStyleClass().remove("me-cell");}

        //VBoxMain.getChildren().addAll(nameLabel,butTest);
        VBoxMain.setSpacing(1);
        HBoxMain.getChildren().addAll(butMessage,VBoxMain);
        HBoxMain.setAlignment(Pos.CENTER_LEFT);
        HBoxMain.setSpacing(10);
        mainPane.getChildren().add(HBoxMain);
        return mainPane;
    }
}
