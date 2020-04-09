package appGui_Tab;

import appGui_User.UserCellFactory;
import context.User;
import javafx.scene.control.ListView;

public class MessageView extends ListView {
    public MessageView() {
        this.setCellFactory(new MessageCellFactory());
        this.getStylesheets().add(getClass().getResource("message.css").toExternalForm());
    }
}
