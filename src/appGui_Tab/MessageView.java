package appGui_Tab;

import appGui_User.UserCellFactory;
import context.User;
import javafx.scene.control.ListView;

public class MessageView extends ListView {
    public MessageView() {
        this.setCellFactory(new MessageCellFactory());
        //TODO - Remove this : Adding test usernames into userlist
        this.getItems().addAll(new MessageUI("karim 93150","rends mon quade"),new MessageUI("Chahid","eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee\\neeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"));
        this.getStylesheets().add(getClass().getResource("message.css").toExternalForm());
    }
}
