package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.CadastrarUsuario;
import main.Principal;

public class CadastrarUsuarioController implements Initializable {

	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnVoltar;

	@FXML
	private TextField txtBairro;

	@FXML
	private TextField txtCidade;

	@FXML
	private TextField txtComplemento;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtNumero;

	@FXML
	private TextField txtRua;

	@FXML
	private TextField txtTelefone;

	@FXML
	void onBtnCadastrarAction(ActionEvent event) {

	}

	@FXML
	void onBtnVoltarAction(ActionEvent event) {
		Principal principal = new Principal();
		close();
		try {
			principal.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void close() {
		CadastrarUsuario.getStage().close();
	}
}
