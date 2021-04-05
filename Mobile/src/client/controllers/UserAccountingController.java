package client.controllers;

import client.entityClass.Accounting;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sample.ErrorWindow;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserAccountingController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Accounting> accountingTable;

    @FXML
    private TableColumn<Accounting, String> idColumn;

    @FXML
    private TableColumn<Accounting, String> vendercodeColumn;

    @FXML
    private TableColumn<Accounting, String> dateColumn;

    @FXML
    private TableColumn<Accounting, String> amountColumn;

    @FXML
    private TableColumn<Accounting, String> loginColumn;

    @FXML
    private TextField amount;

    @FXML
    private ChoiceBox<String> vendercodeBox;

    @FXML
    private TextField date;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        fillTableView();
        client.send("getVendercode");
        ArrayList<String> vendercodeList = client.receiveResultList();
        ObservableList<String> vendercode = FXCollections.observableArrayList(vendercodeList);
        vendercodeBox.setItems(vendercode);
    }

    public void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getAccounting");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Accounting> accountings = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 5);
            Accounting accounting = new Accounting();
            accounting.setId_accounting(Integer.valueOf(infoString[0]));
            accounting.setLogin(infoString[1]);
            accounting.setVendercode(infoString[2]);
            accounting.setDate(infoString[3]);
            accounting.setAmount(Integer.valueOf(infoString[4]));
            accountings.add(accounting);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_accounting"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        vendercodeColumn.setCellValueFactory(new PropertyValueFactory<>("vendercode"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        accountingTable.setItems(accountings);

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dateNow = new Date();
        date.setText(String.valueOf(dateFormat.format(dateNow)));
    }

    @FXML
    void add() {
        if (date.getText().equals("") || amount.getText().equals("") || vendercodeBox.getValue() == null) {
            ErrorWindow.display("Введите все данные!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("addAccounting " + SignInController.login + " " +
                vendercodeBox.getValue() + " " + date.getText() + " " + amount.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ErrorWindow.display("Продукция добавлена успешно!");
            vendercodeBox.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("userPresence", "");
        } else {
            ErrorWindow.display("Ошибка добавления!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("userMenu", "");
    }
}
