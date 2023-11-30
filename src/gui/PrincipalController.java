package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import main.Principal;
import main.Sobre;
import main.emprestimo.Devolucao;
import main.emprestimo.Emprestar;
import main.emprestimo.Renovar;
import main.livro.AtualizarLivro;
import main.livro.BuscarLivro;
import main.livro.CadastrarLivro;
import main.usuario.AtualizarUsuario;
import main.usuario.BuscarUsuario;
import main.usuario.CadastrarUsuario;

public class PrincipalController {

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
	private MenuItem menuEmprestimoRenovar;

	@FXML
	private MenuItem menuEmprestimoBuscar;

	@FXML
	void onMenuAjudaSairAction(ActionEvent event) {
		close();
	}

	@FXML
	void onMenuAjudaSobreAction(ActionEvent event) {
		Sobre sobre = new Sobre();
		try {
			sobre.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onMenuAtualizaLivroAction(ActionEvent event) {
		AtualizarLivro atualizarLivro = new AtualizarLivro();
		try {
			atualizarLivro.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onMenuAtualizaUsuarioAction(ActionEvent event) {
		AtualizarUsuario atualizarUsuario = new AtualizarUsuario();
		try {
			atualizarUsuario.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onMenuBuscaLivroAction(ActionEvent event) {
		BuscarLivro buscarLivro = new BuscarLivro();
		try {
			buscarLivro.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onMenuBuscaUsuarioAction(ActionEvent event) {
		BuscarUsuario buscarUsuario = new BuscarUsuario();
		try {
			buscarUsuario.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onMenuCadastraLivroAction(ActionEvent event) {
		CadastrarLivro cadastrarLivro = new CadastrarLivro();
		try {
			cadastrarLivro.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onMenuCadastraUsuarioAction(ActionEvent event) {
		CadastrarUsuario cadastrarUsuario = new CadastrarUsuario();
		try {
			cadastrarUsuario.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onMenuDevolucaAction(ActionEvent event) {
		Devolucao devolucao = new Devolucao();
		try {
			devolucao.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onMenuEmprestarAction(ActionEvent event) {
		Emprestar emprestar = new Emprestar();
		try {
			emprestar.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onMenuEmprestimoRenovarAction(ActionEvent event) {
		Renovar renovar = new Renovar();
		try {
			renovar.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		Principal.getStage().close();
	}
}
