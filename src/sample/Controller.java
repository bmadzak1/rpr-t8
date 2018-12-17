package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.File;

public class Controller {

    public TextField searchField;
    public Button searchBtn;
    public ListView<String> searchList;
    SimpleStringProperty searchText;
    Thread search;

    public Controller(){
        searchText = new SimpleStringProperty("");
    }

    public ListView getSearchList() {
        return searchList;
    }

    public void setSearchList(ObservableList<String> list) {
        searchList.itemsProperty().setValue(list);
    }

    public SimpleStringProperty searchTextProperty(){
        return searchText;
    }

    public String getSearchText(){
        return searchText.get();
    }

    @FXML
    public void initialize(){
        searchField.textProperty().bindBidirectional(searchText);
    }

    public void btnPressed(ActionEvent actionEvent) {
        if(searchField.textProperty().get() == "")
            return;
        search = new Thread(new Search(searchField.textProperty().get(), this));
        search.start();
    }
}
