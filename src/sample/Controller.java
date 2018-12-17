package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    public TextField searchField;
    public Button searchBtn;
    public ListView<String> searchList;
    public Button cancelBtn;
    public ProgressIndicator progress;
    SimpleStringProperty searchText;
    Thread search;
    Thread searchIndicator;

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
        progress.setVisible(false);
    }

    public void btnPressed(ActionEvent actionEvent) {
        if(searchField.textProperty().get() == "")
            return;
        searchBtn.setDisable(true);
        cancelBtn.setDisable(false);
        progress.setVisible(true);
        search = new Thread(new Search(searchField.textProperty().get(), this));
        search.start();
    }

    public void cancelPressed(ActionEvent actionEvent) {
        searchBtn.setDisable(false);
        cancelBtn.setDisable(true);
        progress.setVisible(false);
        search.interrupt();
    }

    public void changeButtons(){
        searchBtn.setDisable(false);
        cancelBtn.setDisable(true);
        progress.setVisible(false);
    }
}
