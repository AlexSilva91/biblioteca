package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {
	private static String caminho = "/gui/login.fxml";

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			stage.setTitle("Login");
			//Não permite redimensionar
			stage.resizableProperty().setValue(Boolean.FALSE);
			
			Parent root = FXMLLoader.load(getClass().getResource(caminho));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}