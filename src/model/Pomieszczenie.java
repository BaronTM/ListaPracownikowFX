package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Pomieszczenie extends Pane{
	

	private Rectangle rect;
	private Label text;

	public Pomieszczenie(double x, double y, double width, double height, String txt) {
		super();
		this.setMaxSize(width, height);
		this.setPrefSize(width, height);
		this.getStylesheets().add(getClass().getResource("/view/biuro.css").toExternalForm());
		this.setLayoutX(x);
		this.setLayoutY(y);
		rect = new Rectangle(0, 0, width, height);
		rect.getStyleClass().add("pomieszczenie");
		text = new Label(txt);
		text.getStyleClass().add("pomieszczenieLab");
		text.setMaxSize(width, height);
		text.setPrefSize(width, height);
		text.setAlignment(Pos.CENTER);
		this.getChildren().addAll(rect, text);
	}

}
