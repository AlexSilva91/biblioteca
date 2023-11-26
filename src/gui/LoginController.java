package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.Login;
import main.Principal;
import main.RedefinirSenha;
import main.controller.UsuarioController;

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
	private UsuarioController usuarioValidation = new UsuarioController();

	@FXML
	public void onBtnSairChangeAction() {
		this.close();
	}

	@FXML
	public void onBtnEntrarChangeAction() {
//		if (usuarioValidation.validLogin(txtCpf.getText(), txtSenha.getText())) {
//			this.entrar();
//		} else {
//			Alerts.showAlert("ERRO!", "Login inválido!", null, AlertType.ERROR);
//		}
		
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
		//mascaraCPF(txtCpf);

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

	public static void mascaraCPF(TextField textField) {

		textField.setOnKeyTyped((KeyEvent event) -> {
			if ("0123456789".contains(event.getCharacter()) == false) {
				event.consume();
			}

			if (event.getCharacter().trim().length() == 0) { // apagando

				if (textField.getText().length() == 4) {
					textField.setText(textField.getText().substring(0, 3));
					textField.positionCaret(textField.getText().length());
				}
				if (textField.getText().length() == 8) {
					textField.setText(textField.getText().substring(0, 7));
					textField.positionCaret(textField.getText().length());
				}
				if (textField.getText().length() == 12) {
					textField.setText(textField.getText().substring(0, 11));
					textField.positionCaret(textField.getText().length());
				}

			} else { // escrevendo

				if (textField.getText().length() == 14)
					event.consume();

				if (textField.getText().length() == 3) {
					textField.setText(textField.getText() + ".");
					textField.positionCaret(textField.getText().length());
				}
				if (textField.getText().length() == 7) {
					textField.setText(textField.getText() + ".");
					textField.positionCaret(textField.getText().length());
				}
				if (textField.getText().length() == 11) {
					textField.setText(textField.getText() + "-");
					textField.positionCaret(textField.getText().length());
				}

			}
		});

		textField.setOnKeyReleased((KeyEvent evt) -> {

			if (!textField.getText().matches("\\d.-*")) {
				textField.setText(textField.getText().replaceAll("[^\\d.-]", ""));
				textField.positionCaret(textField.getText().length());
			}
		});

	}
}
