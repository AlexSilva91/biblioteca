package gui.usuario;

import gui.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import main.validations.EnderecoValidations;
import main.validations.UsuarioValidation;
import model.entities.Endereco;
import model.entities.Usuario;

public class BuscarUsuarioController {
	private Usuario usuario = new Usuario();
	private Endereco endereco = new Endereco();
	@FXML
	private CheckBox CheckAtivo;

	@FXML
	private CheckBox CheckInativo;

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
	void onBtnBuscarAction(ActionEvent event) {
		/*
		 * CheckAtivo.setSelected(true); CheckAtivo.setDisable(true);
		 * CheckInativo.setDisable(true);
		 */
		UsuarioValidation usuarioValidation = new UsuarioValidation();
		EnderecoValidations enderecoValidation = new EnderecoValidations();
		clearTexts();
		try {
			this.usuario = usuarioValidation.buscaUsuario(txtId.getText());
			if (this.usuario != null) {
				this.endereco = enderecoValidation.getEnderecoFindByIdUser(usuario.getCpf());
				setTexts(this.usuario, this.endereco);
			}else {
				Alerts.showAlert("ERRO!", "Usuário não encontrado!", null, AlertType.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTexts(Usuario usuario, Endereco endereco) {
		txtNome.setText(usuario.getNome());
		txtCpf.setText(Long.toString(usuario.getCpf()));
		txtTelefone.setText(Long.toString(usuario.getContato()));
		if (usuario.getStatus()) {
			CheckAtivo.setSelected(true);
			CheckAtivo.setDisable(true);
			CheckInativo.setDisable(true);
		} else {
			CheckAtivo.setDisable(true);
			CheckInativo.setSelected(true);
			CheckInativo.setDisable(true);
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
}
