package gui;

import domain.DomainController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewGameScreenController extends VBox implements Initializable {

    private DomainController dc;
    @FXML
    private ComboBox<Integer> cbNumber;
    @FXML
    private VBox vbTextFields;
    @FXML
    private Button btnStart;

    public NewGameScreenController() {
        this.dc = new DomainController();
        FXMLLoader f = new FXMLLoader(getClass().getResource("NewGameScreen.fxml"));
        f.setRoot(this);
        f.setController(this);
        try {
            f.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getStyleClass().add("vbox");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbNumber.getItems().addAll(2,3,4,5,6);
        cbNumber.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> showPlayerBoxes((int) newValue));
    }

    private void showPlayerBoxes(int number) {
        this.vbTextFields.getChildren().clear();
        for (int i = 0; i < number; i++) {
            this.vbTextFields.getChildren().add(new TextField());
        }
    }

    @FXML
    private void start() {
        List<String> names = new ArrayList<>();
        for (Node n : this.vbTextFields.getChildren()) {
            names.add(((TextField) n).getText());
        }
        dc.addPlayers(names);
        Stage stage = (Stage) getScene().getWindow();
        stage.setScene(new Scene(new GuessScreenController(this.dc)));
        stage.centerOnScreen();
    }

}
