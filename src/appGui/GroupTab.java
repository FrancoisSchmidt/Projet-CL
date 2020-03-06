package appGui;
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
        Button butSend = new Button("SEND"); //created here to be able to call butSend in textField key press event
        //Send text field
        TextArea chatSend = new TextArea();
        chatSend.setOnKeyPressed((e -> {
            if(new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN).match(e)) {
                chatSend.appendText("\n");}
            else if (e.getCode() == KeyCode.ENTER) {
                butSend.fire();}
        }));

        //Send button
        //... declaration of button is above
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
        bottomLayout.getChildren().addAll(chatSend,butSend);
        VBoxMain.getChildren().addAll(chatField,bottomLayout);
        this.setContent(VBoxMain);
    }
}
