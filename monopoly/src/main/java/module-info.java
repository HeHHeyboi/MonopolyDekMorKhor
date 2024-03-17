module com.example {
    requires javafx.fxml;
    requires transitive javafx.base;
    requires javafx.controls;
    requires transitive javafx.graphics;
    
    
    opens com.example to javafx.fxml;
    exports com.example;
}
