package sample;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Validator implements Runnable{

    SendController controller;

    public Validator(SendController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        String adress = "http://c9.etf.unsa.ba/proba/postanskiBroj.php?postanskiBroj=";
        adress += controller.pBrojField.textProperty().get();
        try{
            URL url = new URL(adress);
            InputStreamReader inputReader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputReader);
            if(reader.readLine().toUpperCase().equals("OK")){
                Platform.runLater(() -> controller.setpBrojFieldValue(true));
            }else{
                Platform.runLater(() -> controller.setpBrojFieldValue(false));
            }
        }catch (Exception e){

        }
    }
}
