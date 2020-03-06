package appGui;

import javafx.scene.Scene;
import javafx.application.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
            HBox.setHgrow(HBoxMain,Priority.ALWAYS);
            VBox.setVgrow(rightArea,Priority.ALWAYS);
            rightArea.setMaxWidth(300);

            //User list View
            //TODO
            ListView userList = new ListView();
            VBox.setVgrow(userList,Priority.ALWAYS);

            //Opening main Tab
            //TODO
            //testTab
            Tab testTab = new GroupTab("testTab",0);
            Tab testTabmanual = new Tab("Manual Tab");
            chatPane.getTabs().addAll(testTab);
            HBox.setHgrow(chatPane,Priority.ALWAYS);

            //Setting up Pane areas
            //TODO
            rightArea.getChildren().addAll(userList);
            HBoxMain.getChildren().addAll(chatPane,rightArea);

            //Setting up primaryStage
            StackPane mainPane = new StackPane();
            mainPane.getChildren().addAll(HBoxMain);
            StackPane.setMargin(HBoxMain, new Insets(10,10,10,10));
            this.primaryStage.setScene(new Scene(mainPane, 1000, 600));
            this.primaryStage.show();

        } catch(Exception e) {e.printStackTrace();}
    }
}
