module com.teamwork.courseselection {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens com.teamwork.courseselection to javafx.fxml;
    opens com.teamwork.courseselection.Model to javafx.fxml;
    exports com.teamwork.courseselection;
    exports com.teamwork.courseselection.Model;
}