package gui.usuario;

import gui.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import main.validations.UsuarioValidation;
import model.entities.Endereco;
import model.entities.Usuario;
import model.services.EnderecoService;
import model.services.UsuarioService;

public class AtualizarUsuarioController {
	private Usuario usuario = new Usuario();
	private Endereco endereco = new Endereco();
	private UsuarioValidation usuarioValidation = new UsuarioValidation();

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
		this.usuario.setCpf(Long.parseLong(txtCpf.getText()));
		this.usuario.setContato(Long.parseLong(txtTelefone.getText()));
		this.usuario.setNome(txtNome.getText().toLowerCase());

		if (CheckAtivo.selectedProperty().getValue()) {
			this.usuario.setStatus(true);
			System.out.println("\nCheckAtivo -> " + CheckAtivo.selectedProperty().getValue());
		}
		if (CheckInativo.selectedProperty().getValue()) {
			this.usuario.setStatus(false);
			System.out.println("\nCheckInativo -> " + CheckInativo.selectedProperty().getValue());
		}
		/**
		 * set endereço
		 */
		this.endereco.setBairro(txtBairro.getText().toLowerCase());
		this.endereco.setCidade(txtCidade.getText().toLowerCase());
		this.endereco.setComplemento(txtComplemento.getText().toLowerCase());
		this.endereco.setRua(txtRua.getText().toLowerCase());
		this.endereco.setNumero(txtNumero.getText().toLowerCase());
		if (endereco != null) {
			this.usuario.setEndereco(endereco);
		}
		/**
		 * Salva usuário e endereço (caso não seja nulo) E limpa os campos preenchidos
		 */
		System.out.println(usuario.getEndereco().getId());

		if (this.usuarioValidation.atualizarUsuario(usuario)) {
			Alerts.showAlert("Atualizado!", "Dados atualizados!", null, AlertType.INFORMATION);
		} else {
			Alerts.showAlert("Erro!", "Impossível atualizar dados!", null, AlertType.ERROR);
		}

	}

	@FXML
	void onBtnBuscarAction(ActionEvent event) {
		clearTexts();
		try {
			this.usuario = usuarioValidation.buscaUsuario(txtId.getText());
			if (this.usuario != null) {
				setTexts(usuario);
			} else {
				Alerts.showAlert("ERRO!", "Usuário não encontrado!", null, AlertType.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTexts(Usuario usuario) {
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
		if (usuario.getEndereco() != null) {
			txtBairro.setText(usuario.getEndereco().getBairro());
			txtCidade.setText(usuario.getEndereco().getCidade());
			txtComplemento.setText(usuario.getEndereco().getComplemento());
			txtNumero.setText(usuario.getEndereco().getNumero());
			txtRua.setText(usuario.getEndereco().getRua());
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
}
