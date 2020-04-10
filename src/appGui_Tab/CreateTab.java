package appGui_Tab;
import groups.*;
import javafx.scene.*;
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
        /*this.setDisable(true);*/
        //Setting custom css to Tab
        this.getStyleClass().add("addnewtab");

        /*
        Button butTest = new Button("+");
        butTest.setOnAction(actionevent -> {System.out.println("testFired");});
        this.setGraphic(butTest);*/

        this.setContent(createNode());
    }

    public Node createNode() {
        StackPane mainPane = new StackPane();
        VBox VBoxMain = new VBox();
        VBox VBoxGroup = new VBox();

        //GroupName label
        Label labelGroupName = new Label("Join / Create Group :");

        //GroupName TextField
        TextField tfGroupName = new TextField();
        tfGroupName.setPromptText("Enter the group name");
        tfGroupName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (tfGroupName.getText().length() > 30) {
                tfGroupName.setText(oldValue); }
            else if (!oldValue.equals(newValue) && (newValue.matches("[\\s\\\\#%\\[\\]]") || !newValue.matches("^[\\s\\\\#%\\[\\]]"))) {
                tfGroupName.setText(newValue.replaceAll("[\\s\\\\#%\\[\\]]", "")); }
        });

        //Hint label
        Label labelHint = new Label("Characters forbidden : \\ , % , # , [] and blank characters\n30 characters maximum");
        labelHint.getStyleClass().add("labelhint");

        //Create Group button
        Button butCreate = new Button ("Join/Create Group");
        butCreate.setOnAction(actionevent -> {
            String groupName = "#" + tfGroupName.getText();
            if (!groupName.matches("\\s*")&&!groupName.equals("#")) {
                ((ChatPane) this.tabPane).root.monGrosClient.transmettreOrdre("createGroup");
                ClientGestionGroup clientGroup =  new ClientGestionGroup(((ChatPane) this.tabPane).root.monGrosClient.getSocOut(),((ChatPane) this.tabPane).root.username);
                clientGroup.createGroup(groupName);
                tfGroupName.setText("");
            }
        });

        /* Fast create/join shortcut */
        tfGroupName.setOnKeyPressed((e -> {
            if (e.getCode() == KeyCode.ENTER) {
                butCreate.fire();}
        }));

        VBoxGroup.getChildren().addAll(labelGroupName,tfGroupName,labelHint);
        VBoxGroup.setSpacing(10);
        VBoxGroup.setAlignment(Pos.TOP_LEFT);
        VBoxMain.getChildren().addAll(VBoxGroup,butCreate);
        VBox.setMargin(VBoxGroup, new Insets(0, 20, 0, 20));
        VBoxMain.setSpacing(20);
        VBoxMain.setAlignment(Pos.TOP_CENTER);
        mainPane.getChildren().add(VBoxMain);
        mainPane.getStyleClass().add("createC");
        return mainPane;
    }
}
