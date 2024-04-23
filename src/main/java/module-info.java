open module org.example.java_lab_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.google.gson;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

//    opens org.example.java_lab to javafx.fxml, com.google.gson;
    exports org.example.java_lab;
}