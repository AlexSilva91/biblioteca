package gui.usuario;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import main.controller.EnderecoController;
import main.controller.UsuarioController;
import model.entities.Endereco;
import model.entities.Usuario;

public class AtualizarUsuarioController implements Initializable {
	private Usuario usuario = new Usuario();
	private Endereco endereco = new Endereco();
	private UsuarioController usuarioValidation = new UsuarioController();
	private EnderecoController enderecoValidations = new EnderecoController();

	@FXML
	private CheckBox CheckAtivo;

	@FXML
	private CheckBox CheckInativo;

	@FXML
	private Button btnAtualizar;

	@FXML
	private Button btnBuscar;

	@FXML
	private TextField txtBairro;

	@FXML
	private TextField txtCidade;

	@FXML
	private TextField txtComplemento;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtNumero;

	@FXML
	private TextField txtRua;

	@FXML
	private TextField txtTelefone;

	@FXML
	void onBtnAtualizarAction(ActionEvent event) {
		/**
		 * Set usuário
		 */
		this.usuario.setCpf(Long.parseLong(this.txtCpf.getText()));
		this.usuario.setContato(Long.parseLong(this.txtTelefone.getText()));
		this.usuario.setNome(this.txtNome.getText().toLowerCase());
		if (this.CheckAtivo.selectedProperty().getValue()) {
			this.usuario.setStatus(true);
		}
		if (this.CheckInativo.selectedProperty().getValue()) {
			this.usuario.setStatus(false);
		}
		/**
		 * set endereço
		 */
		this.endereco.setBairro(this.txtBairro.getText().toLowerCase());
		this.endereco.setCidade(this.txtCidade.getText().toLowerCase());
		this.endereco.setComplemento(this.txtComplemento.getText().toLowerCase());
		this.endereco.setRua(this.txtRua.getText().toLowerCase());
		this.endereco.setNumero(this.txtNumero.getText().toLowerCase());
		/**
		 * Salva usuário e endereço (caso não seja nulo) E limpa os campos preenchidos
		 */
		if (this.usuarioValidation.atualizarUsuario(this.usuario, this.endereco)) {
			Alerts.showAlert("Atualizado!", "Dados atualizados!", null, AlertType.INFORMATION);
		} else {
			Alerts.showAlert("Erro!", "Impossível atualizar dados!", null, AlertType.ERROR);
		}
	}

	@FXML
	void onBtnBuscarAction(ActionEvent event) {
		clearTexts();
		try {
			this.usuario = usuarioValidation.buscaUsuario(this.txtId.getText());
			if (this.usuario != null) {
				this.endereco = enderecoValidations.getEnderecoFindByIdUser(this.usuario.getCpf());
				setTexts(this.usuario, this.endereco);
			} else {
				Alerts.showAlert("ERRO!", "Usuário não encontrado!", null, AlertType.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTexts(Usuario usuario, Endereco endereco) {
		txtNome.setText(usuario.getNome());
		txtCpf.setText(Long.toString(usuario.getCpf()));
		txtCpf.setDisable(true);
		txtTelefone.setText(Long.toString(usuario.getContato()));
		if (usuario.getStatus()) {
			CheckAtivo.setSelected(true);
			CheckAtivo.setDisable(false);
			CheckInativo.setDisable(false);
		} else {
			CheckAtivo.setDisable(false);
			CheckInativo.setSelected(true);
			CheckInativo.setDisable(false);
		}
		if (endereco != null) {
			txtBairro.setText(endereco.getBairro());
			txtCidade.setText(endereco.getCidade());
			txtComplemento.setText(endereco.getComplemento());
			txtNumero.setText(endereco.getNumero());
			txtRua.setText(endereco.getRua());
		}
	}

	public void clearTexts() {
		txtNome.setText("");
		txtCpf.setText("");
		txtTelefone.setText("");
		CheckAtivo.setSelected(false);
		CheckAtivo.setDisable(false);
		CheckInativo.setDisable(false);
		CheckInativo.setSelected(false);
		txtBairro.setText("");
		txtCidade.setText("");
		txtComplemento.setText("");
		txtNumero.setText("");
		txtRua.setText("");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldMaxLength(txtTelefone, 10);
		Constraints.setTextFieldInterger(txtTelefone);
		// Adiciona ouvinte para o CheckBox 1
		CheckAtivo.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				// Se o CheckBox 1 for selecionado, desativa o CheckBox 2
				CheckInativo.setDisable(true);
			} else {
				// Se o CheckBox 1 for desselecionado, reativa o CheckBox 2
				CheckInativo.setDisable(false);
			}
		});

		// Adiciona ouvinte para o CheckBox 2
		CheckInativo.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				// Se o CheckBox 2 for selecionado, desativa o CheckBox 1
				CheckAtivo.setDisable(true);
			} else {
				// Se o CheckBox 2 for desselecionado, reativa o CheckBox 1
				CheckAtivo.setDisable(false);
			}
		});
	}
}
