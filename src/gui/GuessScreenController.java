package gui;

import domain.DomainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GuessScreenController extends VBox implements Initializable {

    private DomainController dc;
    @FXML
    private VBox vbGuessBoxes;
    @FXML
    private Button btnOK;

    public GuessScreenController(DomainController dc) {
        this.dc = dc;
        FXMLLoader f = new FXMLLoader(getClass().getResource("GuessScreen.fxml"));
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
        vbGuessBoxes.getChildren().clear();
        dc.getPlayers().forEach(p -> {
            HBox box = new HBox();
            box.setAlignment(Pos.CENTER);
            box.setSpacing(10);
            Spinner<Integer> spinner = new Spinner<>();
            spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, dc.getRound(), 0));
            spinner.setMaxWidth(50);
            box.getChildren().addAll(new Label(p), spinner);
            vbGuessBoxes.getChildren().add(box);
        });
    }

    @FXML
    private void ok() {
        List<Integer> guesses = new ArrayList<>();
        vbGuessBoxes.getChildren().forEach(n -> {
            Integer guess = (Integer) ((Spinner) ((HBox) n).getChildren().get(1)).getValue();
            guesses.add(guess);
        });
        dc.addGuesses(guesses);
        Stage stage = (Stage) getScene().getWindow();
        stage.setScene(new Scene(new ResultScreenController(this.dc)));
        stage.centerOnScreen();
    }
}
