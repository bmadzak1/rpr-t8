package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.File;

public class Controller {

    public TextField searchField;
    public Button searchBtn;
    SimpleStringProperty searchText;

    public Controller(){
        searchText = new SimpleStringProperty("");
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

    }
}
