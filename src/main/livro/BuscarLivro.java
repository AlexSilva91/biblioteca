package main.livro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BuscarLivro extends Application {
	private static String caminho = "/gui/fxml/livro/buscarLivro.fxml";
	private static Stage stage;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			stage.setTitle("Buscar livro");
			stage.resizableProperty().setValue(Boolean.FALSE);
			setStage(stage);
			Parent buscarLivro = FXMLLoader.load(getClass().getResource(caminho));
			Scene scene = new Scene(buscarLivro);
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
