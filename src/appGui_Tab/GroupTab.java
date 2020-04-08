package appGui_Tab;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class GroupTab extends Tab {
    public int idGroup;
    public String NameGroup;

    public GroupTab(String name,int id) {
        idGroup = id;
        NameGroup = name;
        this.setText(name);

        //Creating Tab layout
        VBox VBoxMain = new VBox();
        //chat field
        TextArea chatField = new TextArea();
        chatField.setEditable(false);

        //Bottom layount
        HBox bottomLayout = new HBox();
        Button butSend = new Button("\u27A5"); //created here to be able to call butSend in textField key press event
        //Send text field
        TextArea chatSend = new TextArea();
        chatSend.setPromptText("Text here...");
        chatSend.setMaxHeight(100);
        chatSend.setOnKeyPressed((e -> {
            if(new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN).match(e)) {
                chatSend.appendText("\n");}
            else if (e.getCode() == KeyCode.ENTER) {
                butSend.fire();}
        }));

        //Send button
        //... declaration of button is above
        butSend.setPrefSize(100,100);
        butSend.getStyleClass().add("butSend");
        butSend.setTooltip(new Tooltip("Send message [ENTER]"));
        butSend.setOnAction(actionevent -> {
            String textToSend = chatSend.getText();
            chatSend.clear();
            if (!textToSend.equals("\n")) {
                System.out.println("button fired");
                System.out.print(textToSend);
                //TODO - ADD SENDING PROTOCOLE
            }
        });

        //setting up layout
        bottomLayout.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(chatSend,Priority.ALWAYS);
        VBox.setVgrow(chatField,Priority.ALWAYS);
        VBoxMain.setSpacing(5);
        bottomLayout.getChildren().addAll(chatSend,butSend);
        VBoxMain.getChildren().addAll(chatField,bottomLayout);
        this.setContent(VBoxMain);
    }
}