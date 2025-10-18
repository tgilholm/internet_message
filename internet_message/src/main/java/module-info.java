module text_gui.main {
	requires transitive javafx.graphics;
    requires javafx.controls;
	requires javafx.base;
    exports text_gui.main;
    exports text_gui.client;
}
