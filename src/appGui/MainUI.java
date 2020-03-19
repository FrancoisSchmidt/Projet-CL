package appGui;

import appGui_Tab.*;
import javafx.animation.*;
import javafx.scene.Scene;
import javafx.application.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.util.Duration;

public class MainUI extends Application implements IMainUI {
    public Stage primaryStage;
    public ListView onlineUsers;

    public void start(Stage primaryStage) throws Exception {
        try {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("LampaulSkype");
            primaryStage.setMinWidth(300);

            //Creating areas for layout
            HBox HBoxMain = new HBox();
            //MainLeft Area - (Chat tab)
            TabPane chatPane = new TabPane();
            chatPane.setMinWidth(280);
            //MainRight Area - (List of users / Settings / disconnect buttons)
            VBox rightArea = new VBox();
            HBoxMain.setAlignment(Pos.CENTER_RIGHT);
            //HBox.setHgrow(HBoxMain,Priority.ALWAYS);
            //VBox.setVgrow(rightArea,Priority.ALWAYS);
            rightArea.setMaxWidth(300);

            //User list View
            //TODO
            ListView userList = new ListView();
            VBox.setVgrow(userList,Priority.ALWAYS);
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
                    changeSize(rightArea,0);}
                else {
                    butHide.getStyleClass().remove("butShow");
                    butHide.getStyleClass().add("butHide");
                    butHide.setTooltip(new Tooltip("HIDE User List [F8]"));
                    changeSize(rightArea,300);}
            });


            //Opening main Tab
            //TODO
            //testTab
            Tab testTab = new GroupTab("testTab",0);
            Tab testTabmanual = new Tab("Manual Tab");
            chatPane.getTabs().addAll(testTab,testTabmanual,new GroupTab("tszzgezestTab",0));
            HBox.setHgrow(chatPane,Priority.ALWAYS);
            //AddNewTab Tab
            Tab addNewTab = new CreateTab(chatPane);
            chatPane.getTabs().addAll(addNewTab);

            //Setting up Pane areas
            //TODO
            rightArea.getChildren().add(userList);
            HBoxMain.setSpacing(2);
            HBoxMain.getChildren().addAll(chatPane,butHide,rightArea);

            //Keyboard shortcut
            //Show/Hide Online users - F8
            HBoxMain.setOnKeyPressed((e -> {
                if (e.getCode() == KeyCode.F8) {
                    butHide.fire();}
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

        } catch(Exception e) {e.printStackTrace();}
    }

    public void changeSize(final Pane pane, double width) {
        Duration cycleDuration = Duration.millis(500);
        Timeline timeline = new Timeline(
                new KeyFrame(cycleDuration,new KeyValue(pane.maxWidthProperty(),width, Interpolator.EASE_BOTH))
        );
        timeline.play();
        timeline.setOnFinished(event->{
            /* insert code here if you need */
        });
    }
}
