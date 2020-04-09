package appGui;

import appGui_Tab.*;
import appGui_User.*;
import chat.*;
import javafx.animation.*;
import javafx.scene.Scene;
import javafx.application.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.util.Duration;
import launchPattern.*;

public class MainUI extends Application implements IMainUI {
    public Stage primaryStage;
    public UserView userList;
    public ChatPane chatPane;

    public String username;
    public MonGrosClient monGrosClient;
    public MainUI(String username, MonGrosClient monGrosClient) {
        this.username = username;
        this.monGrosClient = monGrosClient;
    }

    public void start(Stage primaryStage) throws Exception {
        try {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("LampaulSkype");
            primaryStage.setMinWidth(300);

            //Creating areas for layout
            HBox HBoxMain = new HBox();
            //MainLeft Area - (Chat tab)
            this.chatPane = new ChatPane(this);
            chatPane.setMinWidth(280);
            //MainRight Area - (List of users / Settings / disconnect buttons)
            VBox rightArea = new VBox();
            HBoxMain.setAlignment(Pos.CENTER_RIGHT);
            //HBox.setHgrow(HBoxMain,Priority.ALWAYS);
            //VBox.setVgrow(rightArea,Priority.ALWAYS);
            rightArea.setMaxWidth(300);


            //User list View
            //Label user
            Label labelUserList = new Label("Online Users");
            labelUserList.setPrefSize(300,20);
            labelUserList.getStyleClass().add("userTitleLabel");
            //ListView
            this.userList = new UserView(this);
            VBox.setVgrow(userList,Priority.ALWAYS);

            //Logout Button
            Button butLogout = new Button("Logout");
            butLogout.setOnAction(actionevent -> {
                try {
                    monGrosClient.transmettreOrdre("loggout");
                    primaryStage.close();
                    new LoginUI().start(new Stage());
                } catch (Exception e) {}
            });
            butLogout.setMaxWidth(300);
            butLogout.setPrefHeight(50);
            VBox.setVgrow(butLogout,Priority.ALWAYS);

            //Show-Hide User list
            Button butHide = new Button("");
            butHide.setMinWidth(20);
            butHide.setMaxWidth(20);
            butHide.setTooltip(new Tooltip("HIDE User List [F8]"));
            butHide.setMaxHeight(Double.MAX_VALUE);
            butHide.getStyleClass().add("butHide");
            butHide.setOnAction(actionevent -> {
                if (rightArea.getMaxWidth()==300) {
                    butHide.getStyleClass().remove("butHide");
                    butHide.getStyleClass().add("butShow");
                    butHide.setTooltip(new Tooltip("SHOW User List [F8]"));
                    HideShow(rightArea,true);}
                else {
                    butHide.getStyleClass().remove("butShow");
                    butHide.getStyleClass().add("butHide");
                    butHide.setTooltip(new Tooltip("HIDE User List [F8]"));
                    HideShow(rightArea,false);}
            });

            //Setting up Pane areas
            //TODO
            rightArea.getChildren().addAll(labelUserList,userList, butLogout);
            HBoxMain.setSpacing(2);
            HBoxMain.getChildren().addAll(chatPane,butHide,rightArea);

            //Keyboard shortcut
            //Show/Hide Online users - F8
            HBoxMain.setOnKeyPressed((e -> {
                /* TODO REMOVE TESTING BOB */
                if (e.getCode() == KeyCode.T) {this.chatPane.writeMessage("bob", "A", "salu");}
                if (e.getCode() == KeyCode.F8) {
                    butHide.fire();
                }
                }));

            //Setting up primaryStage
            StackPane mainPane = new StackPane();
            mainPane.getChildren().addAll(HBoxMain);
            StackPane.setMargin(HBoxMain, new Insets(10,10,10,10));
            Scene myScene = new Scene(mainPane,1000,600);
            myScene.getStylesheets().add(getClass().getResource("testSty.css").toExternalForm());
            this.primaryStage.initStyle(StageStyle.DECORATED);
            this.primaryStage.setScene(myScene);
            this.primaryStage.show();


            /* LINKING UI TO LISTENER THREAD */
            ClientEcoute listenerThread = new ClientEcoute(this,monGrosClient.getSocIn());

            this.primaryStage.setOnCloseRequest(event -> {
                monGrosClient.transmettreOrdre("loggout");
                System.exit(0);
            });

        } catch(Exception e) {e.printStackTrace();}
    }

    public void changeSize(final Pane pane, double width) {
        Duration cycleDuration = Duration.millis(500);
        Timeline timeline = new Timeline(
                new KeyFrame(cycleDuration,new KeyValue(pane.maxWidthProperty(),width, Interpolator.EASE_BOTH)));
        timeline.play();
        timeline.setOnFinished(event->{
            /* insert code here if you need */
        });
    }

    public void HideShow(final Pane pane, boolean hide) {
        //Fading transition
        FadeTransition fade = new FadeTransition();
        fade.setNode(pane);
        fade.setDuration(new Duration(500));
        if (hide) {
            fade.setFromValue(1.0);
            fade.setToValue(0.0);}
        else {
            fade.setFromValue(0.0);
            fade.setToValue(1.0);}
        //Size shift transition
        Duration cycleDuration = Duration.millis(500);

        if (hide) {
            Timeline timeline = new Timeline(new KeyFrame(cycleDuration,new KeyValue(pane.maxWidthProperty(),0, Interpolator.EASE_BOTH)));
            ParallelTransition parallelTransition = new ParallelTransition();
            parallelTransition.getChildren().addAll(fade,timeline);
            parallelTransition.play();}
        else {
            Timeline timeline = new Timeline(new KeyFrame(cycleDuration,new KeyValue(pane.maxWidthProperty(),300, Interpolator.EASE_BOTH)));
            ParallelTransition parallelTransition = new ParallelTransition();
            parallelTransition.getChildren().addAll(fade,timeline);
            parallelTransition.play();}
    }
}
