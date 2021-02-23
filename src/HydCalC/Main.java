package HydCalC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("FXML/HydCalC.fxml");
        //  Parent root = FXMLLoader.load(getClass().getResource("HydCalC.fxml"));
        System.out.println(getClass().getResource("FXML/HydCalC.fxml"));
        System.out.println(getClass().getResource("FXML/imprimer.fxml"));
        URL xmlUrlImprimer = getClass().getResource("FXML/imprimer.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        primaryStage.setTitle("Calculs Hydrauliques");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
