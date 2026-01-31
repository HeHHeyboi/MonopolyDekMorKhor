module app {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.media;
	requires java.xml;

	// เปิดให้ FXMLLoader ใช้ reflection
	opens app to javafx.fxml;
	opens app.monopoly2 to javafx.fxml;

	// export เฉพาะที่ต้องการให้คนอื่นใช้
	exports app;
	exports app.monopoly2;
}
