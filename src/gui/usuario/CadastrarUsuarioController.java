package gui.usuario;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Principal;
import main.usuario.CadastrarUsuario;
import main.validations.UsuarioValidation;
import model.entities.Endereco;
import model.entities.Usuario;
import model.services.EnderecoService;
import model.services.UsuarioService;

public class CadastrarUsuarioController implements Initializable {
	private Usuario usuario = new Usuario();
	private Endereco endereco = new Endereco();
	private UsuarioValidation validation = new UsuarioValidation();
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
		try {
			/**
			 * Set usuário
			 */
			usuario.setCpf(Long.valueOf(txtCpf.getText()));
			usuario.setContato(Long.parseLong(txtTelefone.getText()));
			usuario.setNome(txtNome.getText().toLowerCase());
			/**
			 * set endereço
			 */
			endereco.setBairro(txtBairro.getText().toLowerCase());
			endereco.setCidade(txtCidade.getText().toLowerCase());
			endereco.setComplemento(txtComplemento.getText().toLowerCase());
			endereco.setRua(txtRua.getText().toLowerCase());
			endereco.setNumero(txtNumero.getText().toLowerCase());
			endereco.setIdUser(Long.valueOf(txtCpf.getText()));
			usuario.setEndereco(endereco);
			/**
			 * Salva usuário e endereço (caso não seja nulo) E limpa os campos preenchidos
			 */
			validation.saveUser(usuario, endereco);
			this.setTexts();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public void setTexts() {
		txtBairro.setText("");
		txtCidade.setText("");
		txtComplemento.setText("");
		txtCpf.setText("");
		txtNome.setText("");
		txtNumero.setText("");
		txtRua.setText("");
		txtTelefone.setText("");
	}

	public void close() {
		CadastrarUsuario.getStage().close();
	}
}
