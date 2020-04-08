package appGui_Tab;

import context.User;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.beans.value.ChangeListener;

public class MessageCell extends ListCell<MessageUI> {
    public String from;
    public String msg;
    @Override
    public void updateItem(MessageUI message, boolean empty) {
        super.updateItem(message, empty);
        if (message == null || empty) {
            setGraphic(null);
        }
        else {
            from = message.getFrom();
            msg = message.getMsg();
            setGraphic(this.createCell());
        }
    }

    public Node createCell() {
        HBox HBoxMain = new HBox();

        //From label
        Label labelFrom = new Label(from + " :");
        labelFrom.getStyleClass().add("labelUser");
        //Message label
        Label labelMsg = new Label(msg);
        labelMsg.getStyleClass().add("labelMessage");
        HBoxMain.getChildren().addAll(labelFrom,labelMsg);
        HBoxMain.setAlignment(Pos.TOP_LEFT);
        HBoxMain.setSpacing(2);
        return HBoxMain;
    }
}
