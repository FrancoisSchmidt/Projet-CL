package appGui;

import login.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.*;
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
            this.primaryStage.setResizable(false);
            this.primaryStage.show();


            LoginWin();
        } catch(Exception e) {e.printStackTrace();}
    }

    public void LoginWin() throws Exception {
        mainPane = new VBox();
        //--TEXTFIELDS
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Username");
        usernameTextField.setMinSize(250,40);
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("password");
        passwordTextField.setMinSize(250,40);
        //TextField area
        VBox areaText = new VBox();
        areaText.getChildren().addAll(usernameTextField,passwordTextField);
        VBox.setMargin(areaText, new Insets(10,10,10,10));
        areaText.setSpacing(30);
        areaText.setAlignment(Pos.BOTTOM_CENTER);

        //--BUTTONS--
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
            protocoleLog.transmettreLogin();
            protocoleLog.deconnecterDuServeur();

        });
        Button butNewAccount = new Button("Create new Account");
        butNewAccount.setMinSize(150,40);
        butNewAccount.setOnAction(actionevent -> {
            try {RegisterWin();} catch (Exception e) {e.printStackTrace();}
        });
        //Button Area
        VBox areaButton = new VBox();
        areaButton.getChildren().addAll(butLogin,butNewAccount);
        VBox.setMargin(areaButton, new Insets(10,10,10,10));
        areaButton.setSpacing(30);
        areaButton.setAlignment(Pos.CENTER);


        //Creating main Pane
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(30);
        mainPane.getChildren().addAll(areaText,areaButton);
        primaryStage.setScene(new Scene(mainPane, 300, 400));
        primaryStage.show();
    }

    public void RegisterWin() throws Exception {
        mainPane = new VBox();
        //--TEXTFIELDS--
        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Enter your Username");
        usernameTextField.setMinSize(250,40);
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("Enter your Password");
        passwordTextField.setMinSize(250,40);
        PasswordField verifPasswordTextField = new PasswordField();
        verifPasswordTextField.setPromptText("Confirm Password");
        verifPasswordTextField.setMinSize(250,40);
        //TextField area
        VBox areaText = new VBox();
        areaText.getChildren().addAll(usernameTextField,passwordTextField,verifPasswordTextField);
        VBox.setMargin(areaText, new Insets(10,10,10,10));
        areaText.setSpacing(30);
        areaText.setAlignment(Pos.BOTTOM_CENTER);

        //Buttons
        Button butCreateAccount = new Button("Create New Account");
        butCreateAccount.setMinSize(150,40);
        butCreateAccount.setOnAction(actionevent -> {
            //TODO - Possibility to create new accounts
        });
        Button butLogin = new Button("I already have an account");
        butLogin.setMinSize(150,40);
        butLogin.setOnAction(actionevent -> {
            try {LoginWin();} catch (Exception e) {e.printStackTrace();}
        });
        VBox areaButton = new VBox();
        areaButton.getChildren().addAll(butCreateAccount,butLogin);
        VBox.setMargin(areaButton, new Insets(10,10,10,10));
        areaButton.setSpacing(30);
        areaButton.setAlignment(Pos.CENTER);


        //Creating main Pane
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setSpacing(30);
        mainPane.getChildren().addAll(areaText,areaButton);
        primaryStage.setScene(new Scene(mainPane, 300, 400));
        primaryStage.show();
    }
}
