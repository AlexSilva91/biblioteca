package gui.usuario;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.Principal;
import main.usuario.CadastrarUsuario;
import main.validations.UsuarioValidation;
import model.entities.Endereco;
import model.entities.Usuario;

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
			 * set endereço e Salva usuário e endereço (caso não seja nulo) E limpa os
			 * campos preenchidos
			 */
			if (this.validCampos()) {
				/**
				 * Set usuário
				 */
				usuario.setCpf(Long.valueOf(txtCpf.getText()));
				usuario.setContato(Long.parseLong(txtTelefone.getText()));
				usuario.setNome(txtNome.getText().toLowerCase());
				endereco.setBairro(txtBairro.getText().toLowerCase());
				endereco.setCidade(txtCidade.getText().toLowerCase());
				endereco.setComplemento(txtComplemento.getText().toLowerCase());
				endereco.setRua(txtRua.getText().toLowerCase());
				endereco.setNumero(txtNumero.getText().toLowerCase());
				endereco.setIdUser(Long.valueOf(txtCpf.getText()));
				usuario.setEndereco(endereco);
				validation.saveUser(usuario, endereco);
				this.setTexts();
			} else {
				Alerts.showAlert("Erro!", "Necessário preencher todos os campos!", null, AlertType.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onBtnVoltarAction(ActionEvent event) {
		Principal principal = new Principal();
		this.close();
		try {
			principal.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private boolean validCampos() {
		boolean camposPreenchidos = false;
		List<TextField> textFildes = new ArrayList<TextField>();
		textFildes.add(this.txtCidade);
		textFildes.add(this.txtBairro);
		textFildes.add(this.txtComplemento);
		textFildes.add(this.txtRua);
		textFildes.add(this.txtNumero);
		textFildes.add(this.txtNome);
		textFildes.add(this.txtCpf);
		textFildes.add(this.txtTelefone);
		if (validarTextFields(textFildes)) {
			camposPreenchidos = true;
		}
		return camposPreenchidos;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldMaxLength(txtCpf, 11);
		Constraints.setTextFieldInterger(txtCpf);
		Constraints.setTextFieldMaxLength(txtTelefone, 10);
		Constraints.setTextFieldInterger(txtTelefone);

	}

	private boolean validarTextFields(List<TextField> listEndereco) {
		boolean preenchido = false;
		int index = 0;
		for (TextField textField : listEndereco) {
			if (!textField.getText().trim().isEmpty()) {
				System.out.println("\n" + textField.getId());
			} else {
				index += 1;
			}
		}
		if (index <= 0) {
			preenchido = true;
		}
		return preenchido;
	}

	private void setTexts() {
		txtBairro.setText("");
		txtCidade.setText("");
		txtComplemento.setText("");
		txtCpf.setText("");
		txtNome.setText("");
		txtNumero.setText("");
		txtRua.setText("");
		txtTelefone.setText("");
	}

	private void close() {
		CadastrarUsuario.getStage().close();
	}
}
