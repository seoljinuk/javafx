package com.itgroup.controller;

import com.itgroup.bean.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BarChartController implements Initializable {
    @FXML private ToggleGroup toggleGroup;
    @FXML private RadioButton radioHorizontal;
    @FXML private RadioButton radioVertical;
    @FXML private BorderPane borderPane;

    private BarChart barChart;
    private ObservableList<Product> dataList ; // 그래프에 그려질 데이터 정보

    public void makeChartAll(ObservableList<Product> dataList) {
//        for(Product bean : dataList){
//            List<XYChart.Data<String, Integer>> lists = new ArrayList<>();
//
//            // 단가와 재고의 수치 정보가 너무 큰 차이가 나면 로그 함수를 고려해 보자.
//            lists.add(new XYChart.Data<String, Integer>("단가", bean.getPrice()));
//            lists.add(new XYChart.Data<String, Integer>("재고", bean.getStock()));
//
//            XYChart.Series<String, Integer> series = new XYChart.Series<>();
//            series.setName(bean.getName());
//            series.setData(FXCollections.observableArrayList(lists));
//            barChart.getData().add(series) ;
//        }
//        barChart.setTitle("단가/재고 막대");
    }



    public void makeChart(ObservableList<Product> dataList, boolean isVertical) {
        // 단가에 대한 막대 그래프를 그려 줍니다.
        if(dataList == null){return;}

        this.dataList = dataList;

        if(isVertical){
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            barChart = new BarChart<>(xAxis, yAxis);

            XYChart.Series<String, Number> series = new XYChart.Series<>() ;
            series.setName("단가");

            for(Product bean:dataList){
                series.getData().add(new XYChart.Data<>(bean.getName(), bean.getPrice()));
            }
            barChart.getData().add(series);
            barChart.setTitle("단가 막대 그래프");
            //barChart.setCategoryGap(10.0); // 동일 시리즈 내 요소들의 간격
            barChart.setBarGap(1.0); // 막대 Bar들간의 간격

            borderPane.setCenter(barChart);
        }else{
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            barChart = new BarChart<>(yAxis, xAxis);

            XYChart.Series<Number, String> series = new XYChart.Series<>() ;
            series.setName("단가");

            for(Product bean:dataList){
                series.getData().add(new XYChart.Data<>(bean.getPrice(), bean.getName()));
            }
            barChart.getData().add(series);
            barChart.setTitle("단가 수평 막대 그래프");
            //barChart.setCategoryGap(10.0); // 동일 시리즈 내 요소들의 간격
            barChart.setBarGap(1.0); // 막대 Bar들간의 간격

            borderPane.setCenter(barChart);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("막대 그래프를 그리려고 하시는군요.");

        ChangeListener<Toggle> radioButtonChangeListener = new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldValue, Toggle newValue) {
                RadioButton radio = (RadioButton)newValue;
                String id = radio.getId() ;
                // System.out.println(id);
                if(id.equals("radioVertical")){
                    makeChart(dataList, true);
                }else{
                    makeChart(dataList, false);
                }
            }
        };

        toggleGroup.selectedToggleProperty().addListener(radioButtonChangeListener);

        radioVertical.setSelected(true);
    }

}
