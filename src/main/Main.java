package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {
	// implement your code here
	@Override
	public void start(Stage primaryStage) {
		// implement your code here
	}
	
	public static void main (String [] args) {
		launch(args);
	}

	@Override
	public void stop() throws Exception {
		Platform.exit();
	}
	
	
}
