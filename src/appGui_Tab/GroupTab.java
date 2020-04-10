package appGui_Tab;

import appGui.*;
import javafx.application.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import launchPattern.*;
import chat.*;

import java.io.PrintStream;

public class GroupTab extends Tab {
    public MainUI root;
    public MonGrosClient monGrosClient;
    public String me;
    public String NameGroup;
    public MessageView chatView;

    public GroupTab(MainUI root, String name) {
        this.root = root;
        this.monGrosClient = root.monGrosClient;
        this.me = root.username;
        NameGroup = name;
        this.setText(name);

        //Creating Tab layout
        VBox VBoxMain = new VBox();
        //chat field
        chatView = new MessageView();

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

        //Quit group button - IF GROUP ONLY (General can't me left)
        if (name.substring(0, 1).equals("#") && !name.equals("#General")) {
            HBox HLeave = new HBox();
            HLeave.setStyle("-fx-background-color: hsba(260, 60%, 15%, 0.8)");
            Button butLeave = new Button("  Leave Group");
            butLeave.getStyleClass().add("butleave");
            butLeave.setAlignment(Pos.CENTER_RIGHT);
            butLeave.setOnAction(actionevent -> {
                monGrosClient.transmettreOrdre("quitGroup");
                root.clientGestionGroup.quitGroup(NameGroup);
                this.getTabPane().getTabs().remove(this);
            });
            HLeave.getChildren().add(butLeave);
            HLeave.setAlignment(Pos.TOP_RIGHT);
            VBoxMain.getChildren().add(HLeave);
        }


        //Send button
        //... declaration of button is above
        butSend.setPrefSize(100,100);
        butSend.getStyleClass().add("butSend");
        butSend.setTooltip(new Tooltip("Send message [ENTER]"));
        butSend.setOnAction(actionevent -> {
            String textToSend = chatSend.getText();
            chatSend.clear();
            if (!textToSend.matches("\\s*")) {
                System.out.println("button fired - butSend");
                System.out.print(textToSend);
                //TODO - ADD SENDING PROTOCOLE
                monGrosClient.transmettreOrdre("chat");
                ClientChat clientChat = new ClientChat(monGrosClient.getSocOut(),this.me);
                clientChat.transmettreMessage(NameGroup,textToSend);
            }
        });

        //setting up layout
        bottomLayout.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(chatSend,Priority.ALWAYS);
        VBox.setVgrow(chatView,Priority.ALWAYS);
        VBoxMain.setSpacing(5);
        bottomLayout.getChildren().addAll(chatSend,butSend);
        VBoxMain.getChildren().addAll(chatView,bottomLayout);
        this.setContent(VBoxMain);

        /* remove Notify */
        this.setOnSelectionChanged(actionevent -> {
            this.getStyleClass().remove("notify");
        });
    }

    public void writeMessage(String from, String message) {
        Platform.runLater(()->{chatView.getItems().add(new MessageUI(from,message));});
    }

    public String getNameGroup() {return NameGroup;}
}