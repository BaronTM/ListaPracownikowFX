package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pracownik {
	
	private StringProperty imie;
	private StringProperty nazwisko;
	private StringProperty pokoj;
	private StringProperty rozpoczecie;
	private StringProperty zakonczenie;

	public Pracownik() {
		this.imie = new SimpleStringProperty();
		this.nazwisko = new SimpleStringProperty();
		this.pokoj = new SimpleStringProperty();
		this.rozpoczecie = new SimpleStringProperty();
		this.zakonczenie = new SimpleStringProperty();
	}
	
	public Pracownik(String imie, String nazwisko, int pokoj, int rozpoczecie, int zakonczenie) {
		this.imie = new SimpleStringProperty(imie);
		this.nazwisko = new SimpleStringProperty(nazwisko);
		this.pokoj = new SimpleStringProperty("" + pokoj);
		this.rozpoczecie = new SimpleStringProperty("" + rozpoczecie);
		this.zakonczenie = new SimpleStringProperty("" + zakonczenie);
	}
	
	public StringProperty getImieProperty() {
		return this.imie;
	}
	
	public StringProperty getNazwiskoProperty() {
		return this.nazwisko;
	}
	
	public StringProperty getPokojProperty() {
		return this.pokoj;
	}
	
	public StringProperty getRozpoczecieProperty() {
		return this.rozpoczecie;
	}
	
	public StringProperty getZakonczenieProperty() {
		return this.zakonczenie;
	}
	
	public String getImie() {
		return imie.get();
	}
	
	public String getNazwisko() {
		return nazwisko.get();
	}
	
	public String getPokoj() {
		return pokoj.get();
	}
	
	public String getRozpoczecie() {
		return rozpoczecie.get();
	}
	
	public String getZakonczenie() {
		return zakonczenie.get();
	}
	
	public void odbinduj() {
		imie.unbind();
		nazwisko.unbind();
		pokoj.unbind();
		rozpoczecie.unbind();
		zakonczenie.unbind();
	}
	

	
	

}
