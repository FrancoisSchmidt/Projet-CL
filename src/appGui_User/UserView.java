package appGui_User;

import context.User;
import appGui.*;
import java.util.*;
import javafx.application.*;
import javafx.scene.control.*;

public class UserView extends ListView {
    public String me;
    public MainUI root;
    public UserView(MainUI root) {
        this.root = root;
        this.me = root.username;
        this.setCellFactory(new UserCellFactory());
        //TODO - Remove this : Adding test usernames into userlist
        this.getItems().addAll(new User("Ahmed",null,null),new User("Chahid",null,null),new User("Boubacar",null,null));
        this.getStylesheets().add(getClass().getResource("userCell.css").toExternalForm());
    }

    public void update(String[] connectedUserList) {
        Platform.runLater(() -> {
            List<String> sortedUserList = new ArrayList<String>(Arrays.asList(connectedUserList));
            /* TODO - REMOVE TESTUSER */
            sortedUserList.add("Ahmed");
            sortedUserList.add("Tristan");
            sortedUserList.add("Boubacar");

            Collections.sort(sortedUserList);
            /* clearing listview and creating new one */
            this.getItems().clear();
            for (String user : sortedUserList) {
                if (!user.equals("TODO ADD ME")) {
                    this.getItems().add(new User(user, null, null));
                }
            }
        });
    }
}
