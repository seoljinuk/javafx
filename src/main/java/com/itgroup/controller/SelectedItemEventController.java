package com.itgroup.controller;

import com.itgroup.utility.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.*;

public class SelectedItemEventController implements Initializable {
    @FXML private ComboBox myCombo;
    @FXML private ImageView targetImage;

    private Map<String, String> imageMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageMap.put("아메리카노", "americano01.png");
        imageMap.put("브리오슈", "brioche_01.png");
        imageMap.put("치아바타", "ciabatta_01.png");
        imageMap.put("프렌치 바게트", "french_baguette_01.png");

        Set<String> categorySet = imageMap.keySet();

        ObservableList<String> dataList = FXCollections.observableArrayList(categorySet) ;
        myCombo.setItems(dataList);
    }

    public void comboSelection(ActionEvent actionEvent) {
        String selectedCategory = (String)myCombo.getSelectionModel().getSelectedItem();
        System.out.println("선택된 카테고리 : " + selectedCategory);

        String imageFile = Utility.IMAGE_PATH + imageMap.get(selectedCategory);
        Image image = new Image(getClass().getResource(imageFile).toString());
        targetImage.setImage(image);
    }
}
