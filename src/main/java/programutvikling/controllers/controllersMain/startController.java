package programutvikling.controllers.controllersMain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import programutvikling.MainApp;
import programutvikling.base.Component;
import programutvikling.base.ComponentRegister;
import programutvikling.controllers.controllersHelper.registerController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class startController {

    private ComponentRegister cRegister = new ComponentRegister();

    private Stage stage;

    public void setKomponent(ComponentRegister cRegister) {
        this.cRegister = cRegister;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void btnAdmin (ActionEvent event) throws Exception {
        Parent newparent = (Parent) FXMLLoader.load(this.getClass().getResource("/programutvikling/adminView.fxml"));
        Scene newscene = new Scene(newparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Adminpanel");
        window.setScene(newscene);
        window.setResizable(false);
        window.show();
    }

    @FXML
    private void btnUser(ActionEvent event) throws Exception {
        Parent newparent = (Parent) FXMLLoader.load(this.getClass().getResource("/programutvikling/userView.fxml"));
        Scene newscene = new Scene(newparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Userpanel");
        window.setScene(newscene);
        window.setResizable(false);
        window.show();

    }



}
