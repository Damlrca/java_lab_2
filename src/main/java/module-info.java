module org.example.java_lab_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.java_lab_2 to javafx.fxml;
    exports org.example.java_lab_2;
}