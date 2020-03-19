package appGui;

import login.*;
import Register.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginUI extends Application implements ILoginUI {
    public VBox mainPane;
    public Stage primaryStage;

    public void start(Stage primaryStage) throws Exception {
        try {
            this.primaryStage = primaryStage;
            mainPane = new VBox();
            mainPane.getStyleClass().add("loginWin");
            this.primaryStage.setTitle("LampaulSkype");
            Scene myScene = new Scene(mainPane, 300, 400);
            myScene.getStylesheets().add(getClass().getResource("testSty.css").toExternalForm());
            this.primaryStage.setScene(myScene);
            this.primaryStage.setResizable(false);
            this.primaryStage.show();


            LoginWin();
        } catch(Exception e) {e.printStackTrace();}
    }

    public void LoginWin() throws Exception {
        mainPane = new VBox();
        mainPane.getStyleClass().add("loginWin");
        //--TEXTFIELDS--
        TextField usernameTextField = new TextField();
        usernameTextField.getStyleClass().add("loginTextField");
        usernameTextField.setPromptText("Username");
        usernameTextField.setMinSize(250,40);
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.getStyleClass().add("loginTextField");
        passwordTextField.setPromptText("password");
        passwordTextField.setMinSize(250,40);
        //Error labels
        Label errorUsername = new Label("");
        errorUsername.getStyleClass().add("labelError");
        errorUsername.setAlignment(Pos.CENTER_LEFT);
        Label errorPassword = new Label("");
        errorPassword.getStyleClass().add("labelError");
        errorPassword.setAlignment(Pos.CENTER_LEFT);

        //TextField area
        VBox areaText = new VBox();
        areaText.getChildren().addAll(usernameTextField,errorUsername,passwordTextField,errorPassword);
        VBox.setMargin(areaText, new Insets(10,10,10,10));
        areaText.setSpacing(10);
        areaText.setAlignment(Pos.BOTTOM_CENTER);

        //--BUTTONS--
        Button butLogin = new Button("Login");
        butLogin.setMinSize(150,40);
        butLogin.setOnAction(actionevent -> {
            //creating Login Protocole
            String log = usernameTextField.getText();
            String pwd = passwordTextField.getText();
            //TODO - deal with unNomServeur and port unNumero
            ClientLogin protocoleLog = new ClientLogin("localhost", 6666, log, pwd);

            //Trying to connect
            try {
                //Reset error borders
                usernameTextField.getStyleClass().remove("error");
                passwordTextField.getStyleClass().remove("error");
                //Connecting to server
                protocoleLog.connecterAuServeur();
                protocoleLog.transmettreLogin();
                protocoleLog.deconnecterDuServeur();
            }
            catch (Exception e) {
                //this is a test
                usernameTextField.getStyleClass().add("error");
                passwordTextField.getStyleClass().add("error");
                errorUsername.setText("dommage");
                errorPassword.setText("fromage");
                System.out.print("dommageFromage");}

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
        Scene myScene = new Scene(mainPane, 300, 400);
        myScene.getStylesheets().add(getClass().getResource("testSty.css").toExternalForm());
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public void RegisterWin() throws Exception {
        mainPane = new VBox();
        mainPane.getStyleClass().add("loginWin");
        //--TEXTFIELDS--
        TextField usernameTextField = new TextField();
        usernameTextField.getStyleClass().add("loginTextField");
        usernameTextField.setPromptText("Enter your Username");
        usernameTextField.setMinSize(250,40);
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.getStyleClass().add("loginTextField");
        passwordTextField.setPromptText("Enter your Password");
        passwordTextField.setMinSize(250,40);
        PasswordField verifPasswordTextField = new PasswordField();
        verifPasswordTextField.getStyleClass().add("loginTextField");
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
            String log = usernameTextField.getText();
            String pwd = passwordTextField.getText();
            String verifpwd = verifPasswordTextField.getText();
            ClientRegister protocoleReg = new ClientRegister("localhost", 6666, log, pwd);
            try {
                //Reset error borders
                usernameTextField.getStyleClass().remove("error");
                passwordTextField.getStyleClass().remove("error");
                //Connecting to server
                protocoleReg.connecterAuServeur();
                protocoleReg.transmettreReg();
                protocoleReg.deconnecterDuServeur();
            }
            catch (Exception e) {
                //this is a test
                usernameTextField.getStyleClass().add("error");
                passwordTextField.getStyleClass().add("error");
                System.out.print("dommageFromage");}
            String servAnswer = protocoleReg.transmettreReg();
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
        Scene myScene = new Scene(mainPane, 300, 400);
        myScene.getStylesheets().add(getClass().getResource("testSty.css").toExternalForm());
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
}



//fixing things