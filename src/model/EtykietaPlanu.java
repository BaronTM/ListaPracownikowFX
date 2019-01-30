package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class EtykietaPlanu extends Pane{

	public EtykietaPlanu(double width, double height) {
		super();
		this.setWidth(width);
		this.setHeight(height);
		this.setMaxWidth(width);
		this.setMaxHeight(height);
		this.getStylesheets().add(getClass().getResource("/view/biuro.css").toExternalForm());
		this.getStyleClass().add("etykietaPlanu");
		Label lab = new Label("MAPA\nBIURA");
		lab.getStyleClass().add("etykietaLab");
		lab.setMaxSize(width, height);
		lab.setPrefSize(width, height);
		lab.setAlignment(Pos.CENTER);
		this.getChildren().add(lab);		
	}
}
