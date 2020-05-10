module fx {
    requires javafx.controls;
    requires javafx.fxml;

    opens programutvikling to javafx.fxml;
    opens programutvikling.controllers to javafx.fxml;
    opens programutvikling.controllers.controllersHelper to javafx.fxml;
    opens programutvikling.controllers.controllersMain to javafx.fxml;
    exports programutvikling;
}