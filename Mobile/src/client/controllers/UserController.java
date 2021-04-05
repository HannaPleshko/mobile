package client.controllers;

import client.entityClass.Users;
import client.sample.ErrorWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class UserController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Users> userTable;


    @FXML
    private TableColumn<Users, String> nameColumn;

    @FXML
    private TableColumn<Users, String> surnameColumn;

    @FXML
    private TableColumn<Users, String> loginColumn;

    @FXML
    private TableColumn<Users, String> statusColumn;

    @FXML
    private TableColumn<Users, String> roleColumn;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private ChoiceBox<String> roleBox;


    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        fillTableView();
        ObservableList<String> role = FXCollections.observableArrayList("Администратор", "Пользователь");
        roleBox.setItems(role);
    }

    public void fillTableView() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getUser");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Users> users = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 7);
            Users users1 = new Users();
            users1.setName(infoString[0]);
            users1.setSurname(infoString[1]);
            users1.setLogin(infoString[2]);
            users1.setRole(infoString[3]);
            users1.setStatus(infoString[4]);
            users.add(users1);
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        userTable.setItems(users);
    }

    @FXML
    void add() {
        if (name.getText().equals("") || surname.getText().equals("") || login.getText().equals("") ||
                password.getText().equals("") || roleBox.getValue() == null) {
            ErrorWindow.display("Введите все данные!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("addUser " + name.getText() + " " + surname.getText() + " " +
                login.getText() + " " + password.getText() + " " + roleBox.getValue() + " " + "Активен"));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ErrorWindow.display("Пользователь добавлен успешно!");
            fillTableView();
        } else {
            ErrorWindow.display("Ошибка добавления!");
        }
    }

    public void del() {
        if (userTable.getSelectionModel().getSelectedItem() == null) {
            ErrorWindow.display("Вы не выбрали пользователя!");
            return;
        }
        if (userTable.getSelectionModel().getSelectedItem().getStatus().equals("Уволен")) {
            ErrorWindow.display("Этот пользователь уже уволен!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send("off " + "Уволен" + " " + userTable.getSelectionModel().
                getSelectedItem().getLogin());

        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            fillTableView();
            ErrorWindow.display("Пользователь уволен!");
        } else {
            ErrorWindow.display("Ошибка!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }
}
