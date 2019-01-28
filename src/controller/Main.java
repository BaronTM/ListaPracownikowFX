package controller;
	
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private AnchorPane pane;
	private FXMLLoader loader;
	private Scene scene;
	private MainWindowController mwc;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		mainWindow();
	}

	public void mainWindow() {
		loader = new FXMLLoader(
				Main.class.getResource("/view/MainWindowView.fxml"));
		loader.setResources(ResourceBundle.getBundle(
				"bundles.LangBundle", new Locale("pl", "PL")));
		try {
			pane = loader.load();
			primaryStage.setMinWidth(800);
			primaryStage.setMinHeight(600);
			scene = new Scene(pane);
			mwc = loader.getController();
			mwc.setMain(this);
			mwc.setStage(this.primaryStage);
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
