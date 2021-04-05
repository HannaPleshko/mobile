package client.controllers;

import client.entityClass.Goods;
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

public class GoodsController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<Goods> goodsTable;

    @FXML
    private TableColumn<Goods, String> modelColumn;

    @FXML
    private TableColumn<Goods, String> brandColumn;

    @FXML
    private TableColumn<Goods, String> typeColumn;

    @FXML
    private TableColumn<Goods, String> yearColumn;

    @FXML
    private TableColumn<Goods, String> osColumn;

    @FXML
    private TableColumn<Goods, String> vendercodeColumn;

    @FXML
    private TableColumn<Goods, String> costColumn;

    @FXML
    private TextField cost;

    @FXML
    private TextField model;

    @FXML
    private ChoiceBox<String> osBox;

    @FXML
    private ChoiceBox<String> typeBox;

    @FXML
    private TextField vendercode;

    @FXML
    private ChoiceBox<String> brandBox;

    @FXML
    private ChoiceBox<String> yearBox;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        fillTableView();
        client.send("getOs");
        ArrayList<String> osList = client.receiveResultList();
        ObservableList<String> os = FXCollections.observableArrayList(osList);
        osBox.setItems(os);
        client.send("getBrand");
        ArrayList<String> brandList = client.receiveResultList();
        ObservableList<String> brand = FXCollections.observableArrayList(brandList);
        brandBox.setItems(brand);
        client.send("getYear");
        ArrayList<String> yearList = client.receiveResultList();
        ObservableList<String> year = FXCollections.observableArrayList(yearList);
        yearBox.setItems(year);
        ObservableList<String> type = FXCollections.observableArrayList("Смартфон", "Раскладушка", "Кнопочный", "Слайдер");
        typeBox.setItems(type);
    }

    public void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getGoods");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Goods> goods = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 10);
            Goods goods1 = new Goods();
            goods1.setBrand(infoString[0]);
            goods1.setModel(infoString[1]);
            goods1.setType(infoString[2]);
            goods1.setYear(Integer.valueOf(infoString[3]));
            goods1.setOs(infoString[4] + " " + infoString[5]);
            goods1.setVendercode(infoString[6]);
            goods1.setCost(Double.valueOf(infoString[7]));
            goods.add(goods1);
        }
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        osColumn.setCellValueFactory(new PropertyValueFactory<>("os"));
        vendercodeColumn.setCellValueFactory(new PropertyValueFactory<>("vendercode"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        goodsTable.setItems(goods);
    }

    @FXML
    void add() {
        if (vendercode.getText().equals("") || cost.getText().equals("") || osBox.getValue() == null ||
                brandBox.getValue() == null || yearBox.getValue() == null || typeBox.getValue() == null || model.getText().equals("")) {
            ErrorWindow.display("Введите все данные!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("addGoods " + brandBox.getValue() + " " + model.getText() + " " +
                typeBox.getValue() + " " + yearBox.getValue() + " " + osBox.getValue() + " " +
                vendercode.getText() + " " + cost.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            ErrorWindow.display("Телефон добавлен успешно!");
            fillTableView();
        } else {
            ErrorWindow.display("Ошибка добавления!");
        }
    }

    public void del() {
        if (goodsTable.getSelectionModel().getSelectedItem() == null) {
            ErrorWindow.display("Выберите телефон для удаления!");
        } else {
            ClientInstance.INSTANCE.getInstance().send("delGoods " +
                    goodsTable.getSelectionModel().getSelectedItem().getVendercode());
            if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
                fillTableView();
                ErrorWindow.display("Телефон удален успешно!");
            } else {
                ErrorWindow.display("Ошибка удаления!");
            }
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }

}
