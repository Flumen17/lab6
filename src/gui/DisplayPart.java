package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class DisplayPart extends Label {
	
	public DisplayPart() {
		this.setPadding(new Insets(10));
		this.setFont(new Font(18));
	}
	
	public void update(String text) {
		this.setText(text);
	}
}
