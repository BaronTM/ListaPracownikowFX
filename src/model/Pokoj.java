package model;

import javafx.animation.FillTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Pokoj extends Pane{
	
	private Rectangle rect;
	private Text text;
	private FillTransition ft;

	public Pokoj(int nr, double x, double y, double width, double height) {
		super();
		this.setMaxSize(width, height);
		this.setPrefSize(width, height);
		this.getStylesheets().add(getClass().getResource("/view/biuro.css").toExternalForm());
		this.setLayoutX(x);
		this.setLayoutY(y);
		rect = new Rectangle(0, 0, width, height);
		rect.getStyleClass().add("pokoj");
		char[] chars = new char[3];
		chars[0] = '0';
		chars[1] = '0';
		chars[2] = '0';
		String nrStr = "" + nr;
		for (int i = 0; i < nrStr.length(); i++) {
			chars[2-i] = nrStr.charAt(nrStr.length() - 1 - i);
		}
		text = new Text(chars[0] + "\n" + chars[1] + "\n" + chars[2]);
		text.getStyleClass().add("nrPokoju");
		text.setLayoutX((width/2) - 4);
		text.setLayoutY((height/2) - 11);
		this.getChildren().addAll(rect, text);
		
		
		ft = new FillTransition(Duration.millis(1000), rect, Color.TRANSPARENT, Color.rgb(255,127,127));
		ft.setAutoReverse(true);
		ft.setCycleCount(-1);
	}
	
	public void wybrany(boolean wybor) {
		if (wybor) {
			ft.play();
		} else {
			ft.stop();
			rect.getStyleClass().removeAll("pokoj");
			rect.getStyleClass().add("pokoj");
		}
	}
}
