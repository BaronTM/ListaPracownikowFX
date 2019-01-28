package model;

import java.util.Comparator;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pracownik implements Comparable<Pracownik>, Comparator<Pracownik> {

	private StringProperty imie;
	private StringProperty nazwisko;
	private StringProperty pokoj;
	private StringProperty rozpoczecie;
	private StringProperty zakonczenie;
	private NumberBinding czasPracy;

	public Pracownik() {
		this.imie = new SimpleStringProperty();
		this.nazwisko = new SimpleStringProperty();
		this.pokoj = new SimpleStringProperty();
		this.rozpoczecie = new SimpleStringProperty();
		this.zakonczenie = new SimpleStringProperty();
	}

	public Pracownik(String imie, String nazwisko, String pokoj, String rozpoczecie, String zakonczenie) {
		this.imie = new SimpleStringProperty(imie);
		this.nazwisko = new SimpleStringProperty(nazwisko);
		this.pokoj = new SimpleStringProperty(pokoj);
		this.rozpoczecie = new SimpleStringProperty(rozpoczecie);
		this.zakonczenie = new SimpleStringProperty(zakonczenie);
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

	private int liczCzasPracy() {
		int roz = 0;
		int zak = 0;
		try {
			roz = Integer.parseInt(rozpoczecie.get());
		} catch (NumberFormatException e) {
		}
		try {
			zak = Integer.parseInt(zakonczenie.get());
		} catch (NumberFormatException e) {
		}
		
		if (zak >= roz) {
			return zak - roz;
		} else {
			return 24 - roz + zak;
		}
	}

	@Override
	public int compareTo(Pracownik o) {
		if (this.liczCzasPracy() > o.liczCzasPracy()) {
			return 1;
		} else if (this.liczCzasPracy() < o.liczCzasPracy()) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public int compare(Pracownik o1, Pracownik o2) {
		if (o1.liczCzasPracy() > o2.liczCzasPracy()) {
			return 1;
		} else if (o1.liczCzasPracy() < o2.liczCzasPracy()) {
			return -1;
		} else {
			return 0;
		}
	}

}
