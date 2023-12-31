package gui;

import gui.util.AcessarRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import main.Sobre;

public class SobreController {
	@FXML
	private Hyperlink linkRepositoryGitHub;

	@FXML
	private Button btnVoltar;

	@FXML
	void onClickLinkRepositoryGitHubAction(ActionEvent event) {
		try {
			this.acessarRepository();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onBtnVoltarAction(ActionEvent event) {
		this.close();
	}

	private void close() {
		Sobre.getStage().close();
	}

	private void acessarRepository() throws InterruptedException {
		AcessarRepository acessarRepository = new AcessarRepository();
		Thread acessarRepositorio = new Thread(acessarRepository);
		acessarRepositorio.start();
		Thread.sleep(50);
	}
}
