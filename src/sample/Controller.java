package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void listSelected(MouseEvent mouseEvent) {
        String fileName = searchList.getSelectionModel().getSelectedItem().toString();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("send.fxml"));
            Stage searchStage = new Stage();
            searchStage.setTitle("Search");
            searchStage.setScene(new Scene(root, 430, 320));
            searchStage.setResizable(false);
            searchStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
