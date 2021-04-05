package client.controllers;

import client.entityClass.Accounting;
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

public class UserPresenceController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Accounting> presenceTable;

    @FXML
    private TableColumn<Accounting, String> vendercodeColumn;

    @FXML
    private TableColumn<Accounting, String> amountColumn;

    @FXML
    private TextField amount;

    @FXML
    private ChoiceBox<String> vendercodeBox;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        fillTableView();
        client.send("getVendercodePresence");
        ArrayList<String> vendercodeList = client.receiveResultList();
        ObservableList<String> vendercode = FXCollections.observableArrayList(vendercodeList);
        vendercodeBox.setItems(vendercode);
    }

    public void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getPresence");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Accounting> accountings = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 3);
            Accounting accounting = new Accounting();
            accounting.setVendercode(infoString[0]);
            accounting.setAmount(Integer.valueOf(infoString[1]));
            accountings.add(accounting);
        }
        vendercodeColumn.setCellValueFactory(new PropertyValueFactory<>("vendercode"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        presenceTable.setItems(accountings);
    }

    @FXML
    void add() {
        if (amount.getText().equals("") || vendercodeBox.getValue() == null) {
            ErrorWindow.display("Введите все данные!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("addPresence " + vendercodeBox.getValue() + " " + amount.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ErrorWindow.display("Продукция добавлена успешно!");
            fillTableView();
        } else {
            ErrorWindow.display("Ошибка добавления!");
        }
    }

    @FXML
    void set() {
        if (amount.getText().equals("") || vendercodeBox.getValue() == null) {
            ErrorWindow.display("Введите все данные!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("setPresence " + vendercodeBox.getValue() + " " + amount.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ErrorWindow.display("Количество продукции изменено успешно!");
            vendercodeBox.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("userAccounting", "");
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
