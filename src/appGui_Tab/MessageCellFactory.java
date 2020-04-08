package appGui_Tab;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class MessageCellFactory implements Callback<ListView<MessageUI>, ListCell<MessageUI>> {
    @Override
    public ListCell<MessageUI> call(ListView<MessageUI> listview) {
        return new MessageCell();
    }
}
