package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Login;
import main.RedefinirSenha;

public class RedefinirSenhaController implements Initializable {
	@FXML
	private Button btnConfirmar;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextField txtCPF;

	@FXML
	private TextField txtRepeteSenha;

	@FXML
	private TextField txtSenha;

	@FXML
	void onBtnConfirmarAction(ActionEvent event) {

	}

	@FXML
	void onBtnVoltarAction(ActionEvent event) {
		Login login = new Login();
		close();
		try {
			login.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	public void close() {
		RedefinirSenha.getStage().close();
	}
}
