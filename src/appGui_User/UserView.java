package appGui_User;

import appGui.MainUI;
import context.User;
import javafx.application.Platform;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserView extends ListView {
    public String me;
    public MainUI root;
    public UserView(MainUI root) {
        this.root = root;
        this.me = root.username;
        this.setCellFactory(new UserCellFactory());
        this.getStylesheets().add(getClass().getResource("userCell.css").toExternalForm());
    }

    public void update(String[] connectedUserList) {
        Platform.runLater(() -> {
            List<String> sortedUserList = new ArrayList<String>(Arrays.asList(connectedUserList));
            sortedUserList.remove(me);

            Collections.sort(sortedUserList);
            /* clearing listview and creating new one */
            this.getItems().clear();
            /* adding me top list */
            this.getItems().add(new User(me,null,null));
            for (String user : sortedUserList) {
                this.getItems().add(new User(user, null, null));
            }
        });
    }
}
