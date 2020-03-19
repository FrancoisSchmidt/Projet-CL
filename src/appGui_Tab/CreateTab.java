package appGui_Tab;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class CreateTab extends Tab{
    public TabPane tabPane;

    public CreateTab(TabPane pane) {
        this.tabPane = pane;
        this.setText("+");
        this.setClosable(false);
        //Setting custom css to Tab
        this.getStyleClass().add("addnewtab");
    }
}
