module org.example.java_lab_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.google.gson;

    opens org.example.java_lab_2 to javafx.fxml, com.google.gson;
    exports org.example.java_lab_2;
}