module text_gui.main {
	requires transitive javafx.graphics;
    requires javafx.controls;
	requires javafx.base;
	requires java.desktop;
    exports text_gui.main;
    exports text_gui.client;
}
