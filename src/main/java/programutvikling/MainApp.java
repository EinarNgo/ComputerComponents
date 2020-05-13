package programutvikling;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import programutvikling.controllers.controllersMain.startController;
import programutvikling.controllers.controllersMain.userController;

import java.io.IOException;

public class MainApp extends Application{

    private static Scene scene;
    public static void main(String[] args) {
        Application.launch(args);
    }

    /*
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("startView.fxml"));

        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("Startpanel");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("startView", stage));
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml, Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
        Parent root = (Parent)fxmlLoader.load();
        startController controller = (startController) fxmlLoader.getController();
        controller.setStage(stage);
        return root;
    }


}
