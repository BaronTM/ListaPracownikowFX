package model;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.layout.Pane;

public class PlanBiura extends Pane {
	
	private Map<Integer, Pane> mapaPieter;
	
	public PlanBiura() {
		super();
		this.setWidth(350);
		this.setHeight(200);
		this.setMaxWidth(350);
		this.setMaxHeight(200);
		this.getStylesheets().add(getClass().getResource("/view/biuro.css").toExternalForm());
		this.getStyleClass().add("root");
		tworzMape();
	}
	
	private void tworzMape() {
		mapaPieter = new HashMap<Integer, Pane>();
		EtykietaPlanu etykieta = new EtykietaPlanu(350, 200);
		etykieta.setVisible(true);
		this.getChildren().add(etykieta);
		mapaPieter.put(-1, etykieta);
		for (int i = 0; i <= 5; i++) {
			Pietro p = new Pietro(i, 350, 200);
			p.setVisible(false);
			this.getChildren().add(p);
			mapaPieter.put(i, p);
		}
	}
	
	public void wskazPokoj(int nrPokoju) {
		if ((nrPokoju > 0) && (nrPokoju < 21)) {
			wylaczPietra();
			mapaPieter.get(0).setVisible(true);
			Pietro p = (Pietro) mapaPieter.get(0);
			p.wybierzPokoj(nrPokoju);
		} else if ((nrPokoju > 100) && (nrPokoju < 121)) {
			wylaczPietra();
			mapaPieter.get(1).setVisible(true);
			Pietro p = (Pietro) mapaPieter.get(1);
			p.wybierzPokoj(nrPokoju);
		} else if ((nrPokoju > 200) && (nrPokoju < 221)) {
			wylaczPietra();
			mapaPieter.get(2).setVisible(true);
			Pietro p = (Pietro) mapaPieter.get(2);
			p.wybierzPokoj(nrPokoju);
		} else if ((nrPokoju > 300) && (nrPokoju < 321)) {
			wylaczPietra();
			mapaPieter.get(3).setVisible(true);
			Pietro p = (Pietro) mapaPieter.get(3);
			p.wybierzPokoj(nrPokoju);
		} else if ((nrPokoju > 400) && (nrPokoju < 421)) {
			wylaczPietra();
			mapaPieter.get(4).setVisible(true);
			Pietro p = (Pietro) mapaPieter.get(4);
			p.wybierzPokoj(nrPokoju);
		} else if ((nrPokoju > 500) && (nrPokoju < 521)) {
			wylaczPietra();
			mapaPieter.get(5).setVisible(true);
			Pietro p = (Pietro) mapaPieter.get(5);
			p.wybierzPokoj(nrPokoju);
		} else {
			wylaczPietra();
			mapaPieter.get(-1).setVisible(true);
		}
	}
	
	public void wylaczPietra() {
		for (int i = -1; i < 6; i++) {
			mapaPieter.get(i).setVisible(false);
		}
	}
	
}
