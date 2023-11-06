package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import main.CadastrarUsuario;
import main.Principal;
import main.Sobre;

public class PrincipalController implements Initializable{

	@FXML
	private MenuItem menuAjudaSair;

	@FXML
	private MenuItem menuAjudaSobre;

	@FXML
	private MenuItem menuAtualizaLivro;

	@FXML
	private MenuItem menuAtualizaUsuario;

	@FXML
	private MenuItem menuBuscaLivro;

	@FXML
	private MenuItem menuBuscaUsuario;

	@FXML
	private MenuItem menuCadastraLivro;

	@FXML
	private MenuItem menuCadastraUsuario;

	@FXML
	private MenuItem menuDevolucao;

	@FXML
	private MenuItem menuEmprestar;

	@FXML
	private MenuItem menuEmprestimoAtualizar;

	@FXML
	private MenuItem menuEmprestimoBuscar;

	@FXML
	void onMenuAjudaSairAction(ActionEvent event) {
		close();
	}

	@FXML
	void onMenuAjudaSobreAction(ActionEvent event) {
		Sobre sobre = new Sobre();
		close();
		try {
			sobre.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onMenuAtualizaLivroAction(ActionEvent event) {

	}

	@FXML
	void onMenuAtualizaUsuarioAction(ActionEvent event) {

	}

	@FXML
	void onMenuBuscaLivroAction(ActionEvent event) {

	}

	@FXML
	void onMenuBuscaUsuarioAction(ActionEvent event) {

	}

	@FXML
	void onMenuCadastraLivroAction(ActionEvent event) {

	}

	@FXML
	void onMenuCadastraUsuarioAction(ActionEvent event) {
		CadastrarUsuario cadastrarUsuario = new CadastrarUsuario();
		close();
		try {
			cadastrarUsuario.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onMenuDevolucaAction(ActionEvent event) {

	}

	@FXML
	void onMenuEmprestarAction(ActionEvent event) {

	}

	@FXML
	void onMenuEmprestimoAtualizarAction(ActionEvent event) {

	}

	@FXML
	void onMenuEmprestimoBuscarAction(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void close() {
		Principal.getStage().close();
	}
}
