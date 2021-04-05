package client.controllers;

import client.entityClass.Goods;
import client.sample.ErrorWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserGoodsController {

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
    private TextField search;

    @FXML
    private TextField search1;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        fillTableView();
        search.textProperty().addListener((observable, oldValue, newValue) ->
                filterList(oldValue, newValue));
        search1.textProperty().addListener((observable, oldValue, newValue) ->
                filterList1(oldValue, newValue));
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

    public void filterList(String oldValue, String newValue) {
        ObservableList<Goods> filteredList = FXCollections.observableArrayList();
        if (search == null || (newValue.length() < oldValue.length() || newValue == null)) {
            fillTableView();
        } else {
            newValue = newValue.toUpperCase();
            for (Goods goods : goodsTable.getItems()) {
                String filter = goods.getBrand();
                if (filter.toUpperCase().contains(newValue) || filter.toUpperCase().contains(newValue)) {
                    filteredList.add(goods);
                }
            }
            goodsTable.setItems(filteredList);
        }
    }

    public void filterList1(String oldValue, String newValue) {
        ObservableList<Goods> filteredList = FXCollections.observableArrayList();
        if (search == null || (newValue.length() < oldValue.length() || newValue == null)) {
            fillTableView();
        } else {
            newValue = newValue.toUpperCase();
            for (Goods goods : goodsTable.getItems()) {
                String filter = goods.getVendercode();
                if (filter.toUpperCase().contains(newValue) || filter.toUpperCase().contains(newValue)) {
                    filteredList.add(goods);
                }
            }
            goodsTable.setItems(filteredList);
        }
    }

    public void save() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить в файл");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
                for (Goods goods : goodsTable.getItems()) {
                    outWriter.write(goods.toString());
                    outWriter.newLine();
                }
                outWriter.close();
            } catch (IOException e) {
                ErrorWindow.display("Ошибка записи в файл!");
            }
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("userMenu", "");
    }
}
