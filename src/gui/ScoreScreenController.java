package gui;

import domain.DomainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoreScreenController extends VBox implements Initializable {

    private DomainController dc;

    @FXML
    private Label lblRound;
    @FXML
    private GridPane grid;
    @FXML
    private Button btnNext;

    public ScoreScreenController(DomainController dc) {
        this.dc = dc;
        FXMLLoader f = new FXMLLoader(getClass().getResource("ScoreScreen.fxml"));
        f.setRoot(this);
        f.setController(this);
        try {
            f.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getStyleClass().add("vbox");
        grid.getStyleClass().add("grid-pane");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblRound.setText("Ronde " + dc.getRound());
        int i = 0;
        for (String p : dc.getScores().keySet()) {
            grid.getRowConstraints().add(new RowConstraints(30));
            grid.setAlignment(Pos.CENTER);
            Label name = new Label(p);
            grid.add(name, 0, i);
            Label score = new Label(dc.getScores().get(p).toString());
            grid.add(score, 1, i);
            GridPane.setHalignment(name, HPos.CENTER);
            GridPane.setHalignment(score, HPos.CENTER);
            i++;
        }
    }

    @FXML
    public void nextRound() {
        dc.startNewRound();
        Stage stage = (Stage) getScene().getWindow();
        stage.setScene(new Scene(new GuessScreenController(this.dc)));
        stage.centerOnScreen();
    }
}
