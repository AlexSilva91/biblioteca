package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

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
		System.out.println("Saindo...");
	}

	@FXML
	public void onBtnEntrarChangeAction() {
		System.out.println("Entrando...");
	}

	@FXML
	public void onPrintAction() {
		System.out.println("\nCPF: " + txtCpf.getText() + "\nSenha: " + txtSenha.getText());
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
