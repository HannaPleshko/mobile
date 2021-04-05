package client.controllers;

import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class StatController {

    @FXML
    private PieChart pieChart;

    @FXML
    private Button backButton;

    public void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getStatistic");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<PieChart.Data> pieChartList = FXCollections.observableArrayList(
                new PieChart.Data("Смартфон", Double.valueOf(list.get(0))),
                new PieChart.Data("Раскладушка", Double.valueOf(list.get(1))),
                new PieChart.Data("Кнопочный", Double.valueOf(list.get(2))),
                new PieChart.Data("Слайдер", Double.valueOf(list.get(3)))
        );
        pieChart.setData(pieChartList);
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }
}
