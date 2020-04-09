package appGui_Tab;

import context.User;
import java.lang.Math.*;
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
        this.prefWidthProperty().bind(this.getListView().widthProperty().subtract(5));
        this.setMaxWidth(Math.min(Control.USE_PREF_SIZE,300));
    }

    public Node createCell() {
        HBox HBoxMain = new HBox();

        //From label
        Label labelFrom = new Label(from + " :");
        labelFrom.getStyleClass().add("labelUser");
        labelFrom.setMinWidth(Control.USE_PREF_SIZE);
        //Message label
        Label labelMsg = new Label(msg);
        labelMsg.getStyleClass().add("labelMessage");
        HBox.setHgrow(labelFrom,Priority.ALWAYS);
        HBoxMain.getChildren().addAll(labelFrom,labelMsg);
        HBoxMain.setAlignment(Pos.TOP_LEFT);
        HBoxMain.setSpacing(6);
        return HBoxMain;
    }
}
