package gui;

import domain.DomainController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BonusPointsScreenController extends VBox implements Initializable {

    private DomainController dc;

    @FXML
    private ComboBox<String> cbPlayers;
    @FXML
    private HBox hbOptions;
    @FXML
    private RadioButton rbMermaid;
    @FXML
    private RadioButton rbSkullking;
    @FXML
    private HBox hbNumber;
    @FXML
    private Spinner<Integer> sPirates;
    @FXML
    private Button btnCalcScores;

    public BonusPointsScreenController(DomainController dc) {
        this.dc = dc;
        FXMLLoader f = new FXMLLoader(getClass().getResource("BonusPointsScreen.fxml"));
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
        cbPlayers.getItems().addAll(dc.getCorrectPlayers());
        hbOptions.setVisible(false);
        cbPlayers.getSelectionModel().selectedItemProperty().addListener(observable -> hbOptions.setVisible(true));
        hbNumber.setVisible(false);
        rbSkullking.selectedProperty().addListener(observable -> hbNumber.setVisible(rbSkullking.isSelected()));
        rbMermaid.selectedProperty().addListener(observable -> hbNumber.setVisible(rbSkullking.isSelected()));
        sPirates.setMaxWidth(50);
        sPirates.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3, 0));
    }

    @FXML
    public void calculateScores() {
        if (rbMermaid.isSelected()) {
            dc.addBonusPoints(cbPlayers.getValue());
        } else if (rbSkullking.isSelected()) {
            if (sPirates.getValue() > 0) {
                dc.addBonusPoints(cbPlayers.getValue(), sPirates.getValue());
            }
        }
        dc.calculateScores();

        if (dc.getRound() < 10) {
            Stage stage = (Stage) getScene().getWindow();
            stage.setScene(new Scene(new ScoreScreenController(this.dc)));
            stage.centerOnScreen();
        } else {
            Stage stage = (Stage) getScene().getWindow();
            stage.setScene(new Scene(new WinnerScreenController(this.dc)));
            stage.centerOnScreen();
        }
    }
}
