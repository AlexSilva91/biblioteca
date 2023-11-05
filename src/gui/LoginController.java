package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Login;
import main.Principal;
import main.RedefinirSenha;

public class LoginController implements Initializable {
	@FXML
	private Button btnSair;
	@FXML
	private Button btnEntrar;
	@FXML
	private TextField txtCpf;
	@FXML
	private TextField txtSenha;
	@FXML
	private Hyperlink redefinir;

	@FXML
	public void onBtnSairChangeAction() {
		this.close();
	}

	@FXML
	public void onBtnEntrarChangeAction() {
		this.entrar();
	}

	@FXML
	public void onLinkRedefinirSenhaAction() {
		RedefinirSenha redefinirSenha = new RedefinirSenha();
		this.close();
		try {
			redefinirSenha.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	private void close() {
		Login.getStage().close();
	}
	private void entrar() {
		Principal principal = new Principal();
		this.close();
		try {
			principal.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
