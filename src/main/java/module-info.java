module app {
	requires javafx.fxml;
	requires transitive javafx.base;
	requires javafx.controls;
	requires transitive javafx.graphics;
	requires javafx.media;
	requires java.xml;

	opens app to javafx.fxml;

	exports app;
}
