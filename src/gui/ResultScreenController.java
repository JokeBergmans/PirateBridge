package gui;

import domain.DomainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ResultScreenController extends VBox implements Initializable {

    private DomainController dc;

    @FXML
    private VBox vbResultBoxes;
    @FXML
    private Button btnOK;

    public ResultScreenController(DomainController dc) {
        this.dc = dc;
        FXMLLoader f = new FXMLLoader(getClass().getResource("ResultScreen.fxml"));
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
        createBoxes();
    }

    private void createBoxes() {
        vbResultBoxes.getChildren().clear();
        dc.getPlayers().forEach(p -> {
            HBox box = new HBox();
            box.setAlignment(Pos.CENTER);
            box.setSpacing(10);
            Label name = new Label(p);
            name.setMinWidth(100);
            Spinner<Integer> spinner = new Spinner<>();
            spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, dc.getRound(), 0));
            spinner.setMaxWidth(50);
            box.getChildren().addAll(name, spinner);
            vbResultBoxes.getChildren().add(box);
        });
    }

    @FXML
    public void ok() {
        List<Integer> results = new ArrayList<>();
        vbResultBoxes.getChildren().forEach(n -> {
            Integer result = (Integer) ((Spinner) ((HBox) n).getChildren().get(1)).getValue();
            results.add(result);
        });
        dc.addResults(results);
        Stage stage = (Stage) getScene().getWindow();
        stage.setScene(new Scene(new BonusPointsScreenController(this.dc)));
        stage.centerOnScreen();
    }
}
