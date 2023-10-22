package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private String caminho = "/gui/Main.fxml";

	@Override
	public void start(Stage arg0) throws Exception {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(caminho));
			Scene scene = new Scene(parent);
			arg0.setScene(scene);
			arg0.show();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
