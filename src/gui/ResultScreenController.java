package gui;

import domain.DomainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultScreenController extends VBox implements Initializable {

    private DomainController dc;

    @FXML
    private VBox vbResultBoxes;
    @FXML
    private Button btnCalcScores;

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
        ToggleGroup skullKing = new ToggleGroup();
        ToggleGroup mermaid = new ToggleGroup();
        dc.getPlayers().forEach(p -> {
            HBox box = new HBox();
            box.setAlignment(Pos.CENTER);
            box.setSpacing(10);
            Spinner<Integer> spinner = new Spinner<>();
            spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, dc.getRound(), 0));
            spinner.setMaxWidth(50);
            ToggleButton sk = new ToggleButton("Skull King");
            sk.setToggleGroup(skullKing);
            ToggleButton mm = new ToggleButton("Mermaid");
            mm.setToggleGroup(mermaid);
            box.getChildren().addAll(new Label(p), spinner, sk, mm);
            vbResultBoxes.getChildren().add(box);
        });
    }

    @FXML
    public void calculateScores() {

    }
}
