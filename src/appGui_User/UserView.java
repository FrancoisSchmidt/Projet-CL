package appGui_User;

import context.User;
import javafx.scene.control.*;

public class UserView extends ListView {
    public UserView() {
        this.setCellFactory(new UserCellFactory());
        //TODO - Remove this : Adding test usernames into userlist
        this.getItems().addAll(new User("Ahmed",null,null),new User("Chahid",null,null),new User("Boubacar",null,null));
        this.getStylesheets().add(getClass().getResource("userCell.css").toExternalForm());
    }

    public void update(String[] connectedUserList) {

    }
}
