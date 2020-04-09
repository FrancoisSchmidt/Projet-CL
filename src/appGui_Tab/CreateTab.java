package appGui_Tab;
import appGui_User.UserView;
import javafx.beans.value.ChangeListener;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class CreateTab extends Tab{
    public TabPane tabPane;

    public CreateTab(TabPane pane) {
        this.tabPane = pane;
        this.setText("");
        this.setClosable(false);
        this.setDisable(true);
        //Setting custom css to Tab
        this.getStyleClass().add("addnewtab");

        Button butTest = new Button("+");
        butTest.setOnAction(actionevent -> {System.out.println("testFired");});
        this.setGraphic(butTest);
    }

    public Node createNode() {
        StackPane mainPane = new StackPane();
        VBox VBoxMain = new VBox();

        //GroupName label
        Label labelGroupName = new Label("Enter Group Name :");
        labelGroupName.setAlignment(Pos.CENTER_LEFT);

        //GroupName TextField
        TextField tfGroupName = new TextField();

        //Hint label
        Label labelHint = new Label("Only characters a-z A-Z and numbers are allowed");
        labelHint.setAlignment(Pos.CENTER_LEFT);

        //Create Group button
        Button butCreate = new Button ("Create Group");
        butCreate.setOnAction(actionevent -> {

        });

        VBoxMain.getChildren().addAll(labelGroupName,tfGroupName,labelHint,butCreate);
        VBoxMain.setSpacing(1);
        VBoxMain.setAlignment(Pos.CENTER);
        mainPane.getChildren().add(VBoxMain);
        return mainPane;
    }
}
