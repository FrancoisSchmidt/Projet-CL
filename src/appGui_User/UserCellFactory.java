package appGui_User;

import context.User;
import javafx.scene.control.*;
import javafx.util.*;

public class UserCellFactory implements Callback<ListView<User>, ListCell<User>>{
    @Override
    public ListCell<User> call(ListView<User> listview) {
        return new UserCell();
    }
}
