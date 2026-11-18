module com.mycompany.club.ix {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.club.ix to javafx.fxml;
    opens com.mycompany.club.ix.model to javafx.fxml, javafx.base;

    exports com.mycompany.club.ix;
    exports com.mycompany.club.ix.model;
}
