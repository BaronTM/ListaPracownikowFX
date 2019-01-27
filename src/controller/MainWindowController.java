package controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;
import model.Pracownik;

public class MainWindowController {

	private Main main;

	@FXML
	private Button butWczytaj;
	@FXML
	private Button butZapisz;
	@FXML
	private Button butDodaj;
	@FXML
	private Button butRaport;
	@FXML
	private TableView<Pracownik> tabela;
	@FXML
	private TableColumn<Pracownik, StringProperty> colImie;
	@FXML
	private TableColumn<Pracownik, StringProperty> colNazwisko;
	@FXML
	private TableColumn<Pracownik, StringProperty> colPokoj;
	@FXML
	private Label labImie;
	@FXML
	private Label labNazwisko;
	@FXML
	private Label labPokoj;
	@FXML
	private Label labRozpoczecie;
	@FXML
	private Label labZakonczenie;
	@FXML
	private TextField txtImie;
	@FXML
	private TextField txtNazwisko;
	@FXML
	private TextField txtPokoj;
	@FXML
	private TextField txtRozpoczecie;
	@FXML
	private TextField txtZakonczenie;

	private ObservableList<Pracownik> listaPracownikow;
	private Pracownik aktualnyPracownik;

	public void initialize() {
		listaPracownikow = FXCollections.observableArrayList();
		colImie.setCellValueFactory(new PropertyValueFactory<Pracownik, StringProperty>("imie"));
		colNazwisko.setCellValueFactory(new PropertyValueFactory<Pracownik, StringProperty>("nazwisko"));
		colPokoj.setCellValueFactory(new PropertyValueFactory<Pracownik, StringProperty>("pokoj"));
		tabela.setItems(listaPracownikow);
		txtImie.textProperty().addListener(l -> {
			tabela.refresh();
		});
		txtNazwisko.textProperty().addListener(l -> {
			tabela.refresh();
		});
		txtPokoj.textProperty().addListener(l -> {
			tabela.refresh();
		});
		txtRozpoczecie.textProperty().addListener(l -> {
			tabela.refresh();
		});
		txtZakonczenie.textProperty().addListener(l -> {
			tabela.refresh();
		});
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML
	public void raport() {
	}

	@FXML
	public void dodaj() {
		if (aktualnyPracownik != null) {
			aktualnyPracownik.odbinduj();
		}
		czyscPola();
		aktualnyPracownik = new Pracownik();
		listaPracownikow.add(aktualnyPracownik);
		aktualnyPracownik.getImieProperty().bind(txtImie.textProperty());
		aktualnyPracownik.getNazwiskoProperty().bind(txtNazwisko.textProperty());
		aktualnyPracownik.getPokojProperty().bind(txtPokoj.textProperty());
		aktualnyPracownik.getRozpoczecieProperty().bind(txtRozpoczecie.textProperty());
		aktualnyPracownik.getZakonczenieProperty().bind(txtZakonczenie.textProperty());
	}

	public void czyscPola() {
		txtImie.clear();
		txtNazwisko.clear();
		txtPokoj.clear();
		txtRozpoczecie.clear();
		txtZakonczenie.clear();
	}

}
