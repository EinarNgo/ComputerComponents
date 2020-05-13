package programutvikling;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import programutvikling.base.ComponentRegister;
import programutvikling.controllers.controllersMain.startController;
import programutvikling.controllers.controllersMain.userController;

import java.io.IOException;

public class MainApp extends Application{

    private static Scene scene;
    private static ComponentRegister cRegister = new ComponentRegister();

    public static void main(String[] args) {
        Application.launch(args);
    }

        @Override
        public void start(Stage stage) throws IOException {
            scene = new Scene(loadFXML("startView", stage, cRegister));
            stage.setScene(scene);
            stage.show();
        }

        private static Parent loadFXML(String fxml, Stage stage, ComponentRegister cRegister) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
            Parent root = (Parent)fxmlLoader.load();
            startController controller = (startController) fxmlLoader.getController();
            controller.setStage(stage);
            controller.setKomponent(cRegister);
            return root;
        }


    }

