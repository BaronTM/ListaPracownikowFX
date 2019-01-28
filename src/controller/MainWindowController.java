package controller;

import java.util.function.UnaryOperator;

import javax.swing.JFormattedTextField;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import model.Pracownik;

public class MainWindowController {

	private Main main;
	private Stage stage;

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
	private Tooltip pokojTip;

	public void initialize() {
		listaPracownikow = FXCollections.observableArrayList();
		pokojTip = new Tooltip("Pokoje w biurze są ponumerowane od 1 do 20 i znajdują się na 5 piętrach, upewnij się, że podany "
				+ "przez Ciebie pokój jest prawidłowy.");
		pokojTip.setWrapText(true);
		pokojTip.setMaxWidth(200);
		
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
		
		txtPokoj.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (txtPokoj.getText().length() > 3) {
					txtPokoj.setText(txtPokoj.getText(0, 3));
				} else if (!newValue.matches("\\d*")) {
					txtPokoj.setText(newValue.replaceAll("[^\\d]", ""));
				}
				if (txtPokoj.getText().length() > 0) {
					int pok = Integer.parseInt(txtPokoj.getText());
					if (!((pok > 0 && pok <= 20) || (pok > 100 && pok <= 120) || (pok > 200 && pok <= 220)
							|| (pok > 300 && pok <= 320) || (pok > 400 && pok <= 420) || (pok > 500 && pok <= 520))) {
						pokojTip.show(stage, 
								(txtPokoj.getScene().getWindow().getX() + 550),
								(txtPokoj.getScene().getWindow().getY() + 200));
						
					}
				}
				tabela.refresh();
			}
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
	
	public void setStage(Stage stage) {
		this.stage = stage;
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
