package programutvikling.controllers.controllersMain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import programutvikling.base.Component;
import programutvikling.base.ComponentRegister;

import java.net.URL;
import java.util.ResourceBundle;

public class startController implements Initializable {

    @FXML
    private Button btnAdmin, btnUser;

    @FXML
    private void handleBtn(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        if(event.getSource()==btnAdmin){

            stage = (Stage) btnAdmin.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("/programutvikling/adminView.fxml"));

        }

        else{

            stage = (Stage) btnUser.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("/programutvikling/userView.fxml"));

        }

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ComponentRegister componentRegister = new ComponentRegister();

        componentRegister.addComponent(new Component("Case", "en", "Asus",50,"20.10.2018",8000));
        componentRegister.addComponent(new Component("Motherboard", "to","Kingston",400,"2.2.2010",2000));
        componentRegister.addComponent(new Component("Ram", "fire", "Asus",50,"20.10.2018",8000));
        componentRegister.addComponent(new Component("Harddisk", "fem","Kingston",400,"2.2.2010",2000));
        componentRegister.addComponent(new Component("Power", "seks","Rex",100,"3.3.2003",500));
        componentRegister.addComponent(new Component("Case", "syv", "Asus",50,"20.10.2018",8000));
        componentRegister.addComponent(new Component("Motherboard", "åtte","Kingston",400,"2.2.2010",2000));
        componentRegister.addComponent(new Component("Prosessor", "ni","Rex",100,"3.3.2003",500));
        componentRegister.addComponent(new Component("Ram", "ti", "Asus",50,"20.10.2018",8000));
        componentRegister.addComponent(new Component("Harddisk", "elleve","Kingston",400,"2.2.2010",2000));
        componentRegister.addComponent(new Component("Power", "tolv","Rex",100,"3.3.2003",500));


    }

     /*
    @FXML
    private void btnAdmin(ActionEvent event) throws Exception {
        Parent newparent = (Parent) FXMLLoader.load(this.getClass().getResource("/programutvikling/adminView.fxml"));
        Scene newscene = new Scene(newparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Adminpanel");
        window.setScene(newscene);
        window.show();

    }

    @FXML
    private void btnUser(ActionEvent event) throws Exception {
        Parent newparent = (Parent) FXMLLoader.load(this.getClass().getResource("/programutvikling/userView.fxml"));
        Scene newscene = new Scene(newparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Userpanel");
        window.setScene(newscene);
        window.show();

    }

      */

}
