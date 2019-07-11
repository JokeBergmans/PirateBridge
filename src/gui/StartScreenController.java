package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreenController extends VBox {

    @FXML
    private Button btnStartGame;
    @FXML
    private ImageView image;

    public StartScreenController() {
        FXMLLoader f = new FXMLLoader(getClass().getResource("StartScreen.fxml"));
        f.setRoot(this);
        f.setController(this);
        try {
            f.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        image.setImage(new Image("resources/pirate.png"));
        getStyleClass().add("vbox");
    }

    @FXML
    public void start() {
        Stage stage = (Stage) getScene().getWindow();
        stage.setScene(new Scene(new NewGameScreenController()));
        stage.centerOnScreen();
    }
}
