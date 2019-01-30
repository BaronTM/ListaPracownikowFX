package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.PlanBiura;
import model.Pracownik;

public class MainWindowController {

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
	private Button butExit;
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
	@FXML
	private Tooltip pokojTip;
	@FXML
	private BorderPane panelPodBiuro;

	private ObservableList<Pracownik> listaPracownikow;
	private Pracownik aktualnyPracownik;
	private Pracownik komparator = new Pracownik();
	private PrintWriter printWriter;
	private File selectedFile;
	private Scanner scanner;
	private PlanBiura planBiura;

	public void initialize() {
		listaPracownikow = FXCollections.observableArrayList();
		pokojTip.setText(
				"Pokoje w biurze są ponumerowane od 1 do 20 i znajdują się na 5 piętrach, upewnij się, że podany "
						+ "przez Ciebie pokój jest prawidłowy.");
		colImie.setCellValueFactory(new PropertyValueFactory<Pracownik, StringProperty>("imie"));
		colNazwisko.setCellValueFactory(new PropertyValueFactory<Pracownik, StringProperty>("nazwisko"));
		colPokoj.setCellValueFactory(new PropertyValueFactory<Pracownik, StringProperty>("pokoj"));
		tabela.setItems(listaPracownikow);
		txtImie.textProperty().addListener(l -> {
			listaPracownikow.sort(komparator);
			tabela.refresh();
		});
		txtNazwisko.textProperty().addListener(l -> {
			listaPracownikow.sort(komparator);
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
					planBiura.wskazPokoj(pok);
					if (!((pok > 0 && pok <= 20) || (pok > 100 && pok <= 120) || (pok > 200 && pok <= 220)
							|| (pok > 300 && pok <= 320) || (pok > 400 && pok <= 420) || (pok > 500 && pok <= 520))) {
						pokojTip.show(stage, (txtPokoj.getScene().getWindow().getX() + 550),
								(txtPokoj.getScene().getWindow().getY() + 200));
					} else if (pokojTip.isShowing()) {
						pokojTip.hide();					
					}
				} else {
					planBiura.wskazPokoj(0);
				}
				listaPracownikow.sort(komparator);
				tabela.refresh();
			}
		});
		txtRozpoczecie.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (txtRozpoczecie.getText().length() > 2) {
					txtRozpoczecie.setText(txtRozpoczecie.getText(0, 2));
				} else if (!newValue.matches("\\d*")) {
					txtRozpoczecie.setText(newValue.replaceAll("[^\\d]", ""));
				}
				if (txtRozpoczecie.getText().length() > 0) {
					int roz = Integer.parseInt(txtRozpoczecie.getText());
					if (roz < 0 || roz > 24) {
						txtRozpoczecie.setText(txtRozpoczecie.getText(0, 1));
					}
				}
				listaPracownikow.sort(komparator);
				tabela.refresh();
			}
		});
		txtZakonczenie.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (txtZakonczenie.getText().length() > 2) {
					txtZakonczenie.setText(txtZakonczenie.getText(0, 2));
				} else if (!newValue.matches("\\d*")) {
					txtZakonczenie.setText(newValue.replaceAll("[^\\d]", ""));
				}
				if (txtZakonczenie.getText().length() > 0) {
					int zak = Integer.parseInt(txtZakonczenie.getText());
					if (zak < 0 || zak > 24) {
						txtZakonczenie.setText(txtZakonczenie.getText(0, 1));
					}
				}
				listaPracownikow.sort(komparator);
				tabela.refresh();
			}
		});

		tabela.getSelectionModel().selectedItemProperty().addListener((ov, oldVal, newVal) -> {
			if (aktualnyPracownik != null) {
				if ((aktualnyPracownik.getImie().isEmpty() || aktualnyPracownik.getNazwisko().isEmpty()
						|| aktualnyPracownik.getPokoj().isEmpty() || aktualnyPracownik.getRozpoczecie().isEmpty()
						|| aktualnyPracownik.getZakonczenie().isEmpty()) && (!newVal.equals(aktualnyPracownik))) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Anulować?");
					alert.setHeaderText("Wypełnianie danych nowego pracownika nie zostało zakończone.");
					alert.setContentText("Czy chcesz anulować dodawanie pracownika?");
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						Platform.runLater(new Runnable() {
						    @Override public void run() {
						    	aktualnyPracownik.odbinduj();
								czyscPola();
								listaPracownikow.remove(aktualnyPracownik);
								listaPracownikow.sort(komparator);
								tabela.refresh();
								binduj(newVal);
						}});						
					} else {
						alert.close();
						Platform.runLater(new Runnable() {
						    @Override public void run() {
						    	tabela.getSelectionModel().select(aktualnyPracownik);
						}});		
					}
				} else {
					aktualnyPracownik.odbinduj();
					binduj(newVal);
				}
			} else {
				if (newVal != null) {
					binduj(newVal);
				}
			}
		});
		
		planBiura = new PlanBiura();
		panelPodBiuro.setCenter(planBiura);
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void raport() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Zapisz jako");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("TXT Files", "*.txt"));
		selectedFile = fileChooser.showSaveDialog(stage);
		try {
			printWriter = new PrintWriter(selectedFile);
			for (Pracownik p : listaPracownikow) {
				if (!p.getImieProperty().get().isEmpty() && !p.getNazwiskoProperty().get().isEmpty()
						&& !p.getPokojProperty().get().isEmpty() && !p.getRozpoczecieProperty().get().isEmpty()
						&& !p.getZakonczenieProperty().get().isEmpty()) {
					printWriter.println(p.getImie() + " " + p.getNazwisko() + " " + p.getPokoj() + " "
							+ p.getRozpoczecie() + " " + p.getZakonczenie());
					printWriter.flush();
				}
			}
		} catch (FileNotFoundException e) {
		} catch (NullPointerException npe) {
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}
	}

	@FXML
	public void wczytaj() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Wczytaj dane");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("TXT Files", "*.txt"));
		selectedFile = fileChooser.showOpenDialog(stage);
		try {
			scanner = new Scanner(new BufferedReader(new FileReader(selectedFile)));
			if (aktualnyPracownik != null) {
				aktualnyPracownik.odbinduj();
			}
			czyscPola();
			aktualnyPracownik = null;
			listaPracownikow.clear();
			while (scanner.hasNextLine()) {
				String[] tokeny = scanner.nextLine().split(" ");
				if ((tokeny.length == 5) && !(tokeny[0].length() == 0) && !(tokeny[1].length() == 0)
						&& !(tokeny[2].length() == 0) && !(tokeny[3].length() == 0) && !(tokeny[4].length() == 0)) {
					listaPracownikow.add(new Pracownik(tokeny[0], tokeny[1], tokeny[2], tokeny[3], tokeny[4]));
				}
				listaPracownikow.sort(komparator);
			}
		} catch (FileNotFoundException e) {
		} catch (NullPointerException npe) {
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	@FXML
	public void zapisz() {
		if (selectedFile != null) {
			try {
				printWriter = new PrintWriter(selectedFile);
				for (Pracownik p : listaPracownikow) {
					if (!p.getImieProperty().get().isEmpty() && !p.getNazwiskoProperty().get().isEmpty()
							&& !p.getPokojProperty().get().isEmpty() && !p.getRozpoczecieProperty().get().isEmpty()
							&& !p.getZakonczenieProperty().get().isEmpty()) {
						printWriter.println(p.getImie() + " " + p.getNazwisko() + " " + p.getPokoj() + " "
								+ p.getRozpoczecie() + " " + p.getZakonczenie());
						printWriter.flush();
					}
				}
			} catch (FileNotFoundException e) {
			} catch (NullPointerException npe) {
			} finally {
				if (printWriter != null) {
					printWriter.close();
				}
			}
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Błąd");
			alert.setHeaderText("Brak domyślnego pliku zapisu.");
			alert.setContentText("Czy chcesz utworzyć nowy plik?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				raport();
			} else {
				alert.close();
			}
		}
	}

	@FXML
	public void exit() {
		stage.close();
	}

	@FXML
	public void dodaj() {
		if (aktualnyPracownik != null) {
			if (aktualnyPracownik.getImie().isEmpty() || aktualnyPracownik.getNazwisko().isEmpty()
					|| aktualnyPracownik.getPokoj().isEmpty() || aktualnyPracownik.getRozpoczecie().isEmpty()
					|| aktualnyPracownik.getZakonczenie().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR, "Przed dodaniem nowego pracownika wprowadź dane aktualnego!");
				alert.show();
			} else {
				aktualnyPracownik.odbinduj();
				tworz();
			}
		} else {
			tworz();
		}
		listaPracownikow.sort(komparator);
	}

	public void tworz() {
		czyscPola();
		aktualnyPracownik = new Pracownik();
		listaPracownikow.add(aktualnyPracownik);
		aktualnyPracownik.getImieProperty().bind(txtImie.textProperty());
		aktualnyPracownik.getNazwiskoProperty().bind(txtNazwisko.textProperty());
		aktualnyPracownik.getPokojProperty().bind(txtPokoj.textProperty());
		aktualnyPracownik.getRozpoczecieProperty().bind(txtRozpoczecie.textProperty());
		aktualnyPracownik.getZakonczenieProperty().bind(txtZakonczenie.textProperty());
	}

	public void binduj(Pracownik pracownik) {
		aktualnyPracownik = pracownik;
		txtImie.setText(aktualnyPracownik.getImie());
		txtNazwisko.setText(aktualnyPracownik.getNazwisko());
		txtPokoj.setText(aktualnyPracownik.getPokoj());
		txtRozpoczecie.setText(aktualnyPracownik.getRozpoczecie());
		txtZakonczenie.setText(aktualnyPracownik.getZakonczenie());
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
