package appGui;

import client.ProtocoleLogin;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginUI extends Application implements ILoginUI {
    public VBox mainPane;
    public Stage primaryStage;

    public void start(Stage primaryStage) throws Exception {
        try {
            this.primaryStage = primaryStage;
            mainPane = new VBox();
            this.primaryStage.setTitle("LampaulSkype");
            this.primaryStage.setScene(new Scene(mainPane, 300, 400));
            this.primaryStage.show();

            LoginWin();
        } catch(Exception e) {e.printStackTrace();}
    }

    public void LoginWin() throws Exception {
        mainPane = new VBox();
        //TextFields
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");
        usernameTextField.setMinSize(250,40);
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("password");
        passwordTextField.setMinSize(250,40);

        //Buttons
        Button butLogin = new Button("Login");
        butLogin.setMinSize(150,40);
        butLogin.setOnAction(actionevent -> {
            //creating Login Protocole
            String log = usernameTextField.getText();
            String pwd = passwordTextField.getText();
            //TODO - deal with unNomServeur and port unNumero
            ProtocoleLogin protocoleLog = new ProtocoleLogin("localhost", 6666, log, pwd);

            //Trying to connect
            protocoleLog.connecterAuServeur();
        });
        Button butNewAccount = new Button("Create new Account");
        butNewAccount.setMinSize(150,40);

        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(30);
        mainPane.getChildren().addAll(usernameTextField,passwordTextField,butLogin,butNewAccount);
        primaryStage.setScene(new Scene(mainPane, 300, 400));
        primaryStage.show();
    }

    public void RegisterWin() throws Exception {}
}
