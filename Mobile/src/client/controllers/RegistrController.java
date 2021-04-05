package client.controllers;

import client.sample.ErrorWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrController {

    @FXML
    private TextField userLogin;

    @FXML
    private PasswordField userPassword;


    @FXML
    private TextField userSurname;

    @FXML
    private TextField userName;


    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
    }

    public void registration() {
        if (userLogin.getText().equals("") || userPassword.getText().equals("") ||
                userName.getText().equals("") || userSurname.getText().equals("")) {
            ErrorWindow.display("Введите все данные!");
            return;
        }
        if (userLogin.getLength() > 25 || userPassword.getLength() > 25 ||
                userName.getLength() > 15 || userSurname.getLength() > 25) {
            ErrorWindow.display("Введите все данные!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send(("addUser " + userName.getText() + " " + userSurname.getText() + " " +
                userLogin.getText() + " " + userPassword.getText() + " " + "Пользователь"));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ErrorWindow.display("Клиент добавлен успешно!");
            userLogin.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("signIn", "");
        } else {
            ErrorWindow.display("Ошибка добавления!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("signIn", "");
    }
}
