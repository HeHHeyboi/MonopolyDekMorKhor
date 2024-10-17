module app {
    requires javafx.fxml;
    requires transitive javafx.base;
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.media;
    
    
    opens app to javafx.fxml;
    exports app;
}
