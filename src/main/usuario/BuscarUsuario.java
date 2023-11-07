package main.usuario;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BuscarUsuario extends Application {
	private static String caminho = "/gui/fxml/usuario/buscarUsuario.fxml";
	private static Stage stage;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			stage.setTitle("Buscar usuario");
			stage.resizableProperty().setValue(Boolean.FALSE);
			Parent buscar = FXMLLoader.load(getClass().getResource(caminho));
			Scene scene = new Scene(buscar);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stageArg) {
		stage = stageArg;
	}
}
