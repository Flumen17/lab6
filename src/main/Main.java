package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.Timer;

public class Main extends Application {
	public static Timer timer;
	private static Timer stopwatch1, stopwatch2;
	@Override
	public void start(Stage primaryStage) {
		HBox root = new HBox();
		timer = new Timer("Timer", Timer.COUNT_DOWN);
		stopwatch1 = new Timer("Stopwatch 1", Timer.COUNT_UP);
		stopwatch2 = new Timer("Stopwatch 2", Timer.COUNT_UP);
		root.getChildren().addAll(timer.getTimerGUI(), stopwatch1.getTimerGUI(), stopwatch2.getTimerGUI());
		primaryStage.setTitle("Timer");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	public static void main (String [] args) {
		launch(args);
	}

	@Override
	public void stop() throws Exception {
		Platform.exit();
	}
	
	
}
