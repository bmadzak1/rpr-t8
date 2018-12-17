package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SendController {
    public TextField pBrojField;

    @FXML
    void initialize(){
        pBrojField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                startValidation();
            }
        });
    }

    void startValidation(){
        Validator validator = new Validator(this);
        Thread validationThread = new Thread(validator);
        validationThread.start();
    }

    public void setpBrojFieldValue(Boolean bool){
        if(bool){
            pBrojField.getStyleClass().removeAll("poljeNijeIspravno");
            pBrojField.getStyleClass().add("poljeIspravno");
        }else {
            pBrojField.getStyleClass().removeAll("poljeIspravno");
            pBrojField.getStyleClass().add("poljeNijeIspravno");
        }
    }
}
