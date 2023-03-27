module com.example.w1901982{
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.w1901982 to javafx.fxml;
    exports com.example.w1901982;
}