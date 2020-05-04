module fx {
    requires javafx.controls;
    requires javafx.fxml;

    opens programutvikling to javafx.fxml;
    opens programutvikling.controllers to javafx.fxml;
    exports programutvikling;
}