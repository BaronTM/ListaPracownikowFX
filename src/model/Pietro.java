package model;


import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Pietro extends Pane{
	
	private int pietro;
	private Label pietroLab;
	private Map<Integer, Pokoj> mapaPokoi;
	private Pokoj aktualnieWybrany;
	
	public Pietro(int nr, double width, double height) {
		super();
		this.pietro = nr;
		this.mapaPokoi = new HashMap<>();
		this.setWidth(width);
		this.setHeight(height);
		this.setMaxWidth(width);
		this.setMaxHeight(height);
		this.getStylesheets().add(getClass().getResource("/view/biuro.css").toExternalForm());
		this.getStyleClass().add("pietro");
		Rectangle rect = new Rectangle(0, 0, width, height);
		rect.getStyleClass().add("ramka");
		String tytul = "PARTER";
		switch (pietro) {
		case 1: {
			tytul = "PIĘTRO I";
			break;
		}
		case 2: {
			tytul = "PIĘTRO II";
			break;
		}
		case 3: {
			tytul = "PIĘTRO III";
			break;
		}
		case 4: {
			tytul = "PIĘTRO IV";
			break;
		}
		case 5: {
			tytul = "PIĘTRO V";
			break;
		}
		}
		pietroLab = new Label(tytul);
		pietroLab.getStyleClass().add("pietroLab");
		pietroLab.setMaxSize(width, height);
		pietroLab.setPrefSize(width, height);
		pietroLab.setAlignment(Pos.CENTER);
		this.getChildren().add(rect);
		this.getChildren().add(pietroLab);
		rysujPokoje();
	}
	
	private void rysujPokoje() {
		double x = 0;
		for (int i = 1; i < 13; i++) {
			if (i == 7) {
				x += 50;
			}
			int nr = pietro * 100 + i;
			Pokoj pokoj = new Pokoj(nr, x, 0, 25, 70);
			mapaPokoi.put(nr, pokoj);
			this.getChildren().add(pokoj);
			x += 25;
		}
		
		x = 0;
		for (int i = 13; i < 21; i++) {
			if (i == 17) {
				x += 70;
			}
			int nr = pietro * 100 + i;
			Pokoj pokoj = new Pokoj(nr, x, 130, 35, 70);
			mapaPokoi.put(nr, pokoj);
			this.getChildren().add(pokoj);
			x += 35;
		}
		
		if (pietro == 0) {
			Pomieszczenie pom = new Pomieszczenie(140, 150, 70, 50, "WEJŚCIE");
			this.getChildren().add(pom);
		} else {
			Pomieszczenie pom = new Pomieszczenie(140, 130, 70, 70, "MEETING\nROOM");
			this.getChildren().add(pom);
		}
		
		Pomieszczenie pom = new Pomieszczenie(150, 0, 50, 70, "KLATKA");
		this.getChildren().add(pom);
	}
	
	public void wybierzPokoj(int nr) {
		if (aktualnieWybrany != null) {
			aktualnieWybrany.wybrany(false);
		} 
		aktualnieWybrany = mapaPokoi.get(nr);
		aktualnieWybrany.wybrany(true);
	}

}
