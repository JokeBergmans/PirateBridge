package startUp;

import gui.StartScreenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StartScreenController ssc = new StartScreenController();
        primaryStage.setScene(new Scene(ssc));
        primaryStage.show();
        primaryStage.setTitle("Piraten Bridge");
    }
}
