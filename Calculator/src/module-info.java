module Calculator {
	requires jdk.jshell;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	
	opens application.view to javafx.graphics;
	opens application.controller to javafx.fxml;
	opens application.model to javafx.controls;
	
	exports application.controller;
	exports application.model;
	exports application.view;
	
}