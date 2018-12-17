package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Search implements Runnable {

    String text;
    Controller controller;
    ObservableList<String> fileList;
    File searchFile;

    public ObservableList<String> getSearchResult()
    {
        return fileList;
    }

    @Override
    public void run() {
        for(int i = 0; i < searchFile.list().length; i++) {
            File newFile = new File(searchFile, searchFile.list()[i]);
            searchByFile(newFile);
        }
        Platform.runLater(() -> controller.setSearchList(fileList));
        Platform.runLater(() -> controller.changeButtons());
    }

    Search(String searchText, Controller controller)
    {
        fileList = FXCollections.observableArrayList();
        searchFile = new File("C:\\Test");
        this.controller = controller;
        text = searchText;
    }

    void searchByFile(File f)
    {
        int firstSubstringLetter = 0;
        int lastSubstringLetter = text.length();
        while (lastSubstringLetter <= f.getName().length()) {
            if(f.getName().substring(firstSubstringLetter, lastSubstringLetter).equalsIgnoreCase(text)) {
                fileList.add(f.getPath());
                break;
            }
            firstSubstringLetter++;
            lastSubstringLetter++;
        }

        if(f.list() != null)
            for(int i = 0; i < f.list().length; i++) {
                File newFile = new File(f, f.list()[i]);
                searchByFile(newFile);
            }
        else
            return;
    }
}
