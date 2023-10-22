module com.example.finalatakanaytar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.oracle.database.jdbc;
    requires java.xml;
    requires java.desktop;

    opens finalAtakanAytar to javafx.fxml;
    exports finalAtakanAytar;
}