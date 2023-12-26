package gui.emprestimo;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.controller.EmprestimoControllerMain;
import main.controller.LivroController;
import main.controller.UsuarioController;
import model.entities.Emprestimo;
import model.entities.Livros;
import model.entities.Usuario;

public class EmprestarController implements Initializable {
	private List<String> listStringLivros = new ArrayList<String>();
	private Usuario usuario = new Usuario();
	private Livros livro = new Livros();
	private UsuarioController usuarioController = new UsuarioController();
	private LivroController livroController = new LivroController();
	private static List<Livros> listLivro = new ArrayList<Livros>();
	private Emprestimo emprestimo = new Emprestimo();
	private EmprestimoControllerMain emprestimoControllerMain = new EmprestimoControllerMain();

	@FXML
	private Button btnBusca;

	@FXML
	private Button btnEmprestar;

	@FXML
	private ComboBox<String> cBoxLivros;

	@FXML
	private TextField txtDevolucao;

	@FXML
	private TextField txtEmprestimo;

	@FXML
	private TextField txtBuscaUsuario;

	@FXML
	void onBtnEmprestarAction(ActionEvent event) {
		try {
			if (usuarioValido() && livroSelecionado()) {
				String livroBox = this.cBoxLivros.getValue();
				String[] boxDadosLivro = livroBox.split("-");
				this.livro = this.getLivro(boxDadosLivro[0].strip(), Integer.valueOf(boxDadosLivro[1].strip()));

				if (this.livro.getStatus()) {
					this.emprestimo.setDt_Incial(LocalDate.now());
					this.emprestimo.setDt_Final(EmprestimoControllerMain
							.convertStringEmLocalDate(EmprestimoControllerMain.validDataDevolucao(LocalDate.now())));;
					this.emprestimo.setExemplar(this.livro.getExemplar());
					if (this.livro.getExemplar() > 0) {
						this.livro.setExemplar(this.livro.getExemplar() - 1);
					}
					if (this.livro.getExemplar() == 0) {
						this.livro.setStatus(false);
					}

					this.emprestimo.setLivroId(this.livro.getIsbn());
					this.emprestimo.setTitulo(this.livro.getTitulo());
					if (this.usuario.getStatus()) {
						this.emprestimo.setUsuarioId(this.usuario.getCpf());
						this.emprestimo.setStatus(true);

						this.emprestimoControllerMain.emprestarLivro(this.emprestimo);
						this.livroController.updateBook(this.livro);
					} else {
						Alerts.showAlert("Erro!", "Necessário selecionar um usuário ativo!", null, AlertType.ERROR);
					}
				} else {
					Alerts.showAlert("Erro!", "Livro indisponível!", null, AlertType.ERROR);
				}
			} else {
				Alerts.showAlert("Erro!", "Necessário selecionar o livro e buscar o usuário!", null, AlertType.ERROR);
			}
			this.atualizarLivros();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onBuscaUsuarioAction(ActionEvent event) {
		try {
			if (!this.txtBuscaUsuario.getText().toLowerCase().isEmpty()) {
				this.usuario = this.usuarioController.buscaUsuario(this.txtBuscaUsuario.getText());
				if (this.usuario == null) {
					this.txtBuscaUsuario.setStyle("-fx-border-color: red;");
				} else {
					this.txtBuscaUsuario.setStyle("-fx-border-color: lime;");
				}
			} else {
				Alerts.showAlert("Erro!", "Necessário inserir nome ou CPF!", null, AlertType.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.txtDevolucao.setText(EmprestimoControllerMain.validDataDevolucao(LocalDate.now()));
		this.txtEmprestimo.setText(EmprestimoControllerMain.ValidData(LocalDate.now()));
		this.atualizarLivros();

	}

	@SuppressWarnings("static-access")
	public void atualizarLivros() {
		this.cBoxLivros.getItems().clear();
		try {
			this.listLivro = this.livroController.listAll();
			for (Livros libre : this.listLivro) {
				if (libre.getStatus()) {
					this.listStringLivros.add(libre.getTitulo() + " - " + libre.getAno());
				}
			}
			ObservableList<String> itens = FXCollections.observableList(this.listStringLivros);
			this.cBoxLivros.setItems(itens);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public Livros getLivro(String titulo, int ano) {
		for (Livros libre : this.listLivro) {
			if (libre.getTitulo().equalsIgnoreCase(titulo) && libre.getAno() == ano) {
				this.livro = libre;
			}
		}
		return this.livro;
	}

	public boolean usuarioValido() {
		boolean usuarioValido = false;
		if (this.usuario != null) {
			usuarioValido = true;
		}
		return usuarioValido;
	}

	public boolean livroSelecionado() {
		boolean livroSelecionado = false;
		if (cBoxLivros.getValue() != null) {
			livroSelecionado = true;
		}
		return livroSelecionado;
	}
}
