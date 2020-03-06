package appGui;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainUI {
    public Stage primaryStage;
    public ListView onlineUsers;

    public void start(Stage primaryStage) throws Exception {
        try {
            this.primaryStage = primaryStage;
            StackPane mainPane = new StackPane();
            this.primaryStage.setTitle("LampaulSkype");
            this.primaryStage.setScene(new Scene(mainPane, 1000, 800));
            this.primaryStage.setResizable(false);
            this.primaryStage.show();

            //Creating areas for layout
            HBox HBoxMain = new HBox();
            //MainLeft Area - (Chat tab)
            TabPane chatPane = new TabPane();
            //MainRight Area - (List of users / Settings / disconnect buttons)
            VBox rightArea = new VBox();

            //User list View
            //TODO

            //Opening main Tab


            //Setting up primaryStage
            this.primar

        } catch(Exception e) {e.printStackTrace();}
    }


}
