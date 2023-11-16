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
	private UsuarioService service = new UsuarioService();
	private EnderecoService enderecoService = new EnderecoService();
	private UsuarioValidation usuarioValidation = new UsuarioValidation();
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
		Integer id = Integer.valueOf(txtCpf.getText());
		try {
			if(usuarioValidation.usuarioExiste(id)) {
				Alerts.showAlert("Usuário já existe", "Usuário existente!", null, AlertType.ERROR);
			}else {
				usuario.setStatus(true);
				usuario.setCpf(id);
				usuario.setContato(Integer.valueOf(txtTelefone.getText()));
				usuario.setNome(txtNome.getText());
				
				endereco.setBairro(txtBairro.getText());
				endereco.setCidade(txtCidade.getText());
				endereco.setComplemento(txtComplemento.getText());
				endereco.setRua(txtRua.getText());
				endereco.setNumero(txtNumero.getText());
				usuario.setEndereco(endereco);
				endereco = enderecoService.saveEndereco(endereco);
				usuario = service.saveUsuario(usuario);
				Alerts.showAlert("Salvo com sucesso!", "Salvo!", null, AlertType.INFORMATION);
			}
		}catch (Exception e) {
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

	public void close() {
		CadastrarUsuario.getStage().close();
	}
}
