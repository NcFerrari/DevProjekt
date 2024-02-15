package lp.fe.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 450, 500);
        stage.setScene(scene);
        stage.show();
    }
}
