package gui.emprestimo;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Emprestimo;
import model.entities.Emprestimos;
import model.services.EmprestimoService;


public class RenovarController {

	@FXML
	private Button btnBuscar;
	@FXML
	private Button btnRenovar;
	@FXML
	private TableColumn<Emprestimo, String> columDatFinal;
	@FXML
	private TableColumn<Emprestimo, String> columDatInit;
	@FXML
	private TableColumn<Emprestimo, Integer> columExemplar;
	@FXML
	private TableColumn<Emprestimo, String> columDevolvido;
	@FXML
	private TableColumn<Emprestimo, Integer> columLivroId;
	@FXML
	private TableColumn<Emprestimo, String> columStatus;
	@FXML
	private TableColumn<Emprestimo, String> columTitulo;
	@FXML
	private TableColumn<Emprestimo, Integer> columUsuarioId;
	@FXML
	private TextField txtBusca;
	@FXML
	private TableView<Emprestimos> tblEmprestimo;
	private List<Emprestimo> listEmprestimoPorUsuario = new ArrayList<Emprestimo>();
	private List<Emprestimos> listEmprestimos = new ArrayList<Emprestimos>();
	private ObservableList<Emprestimos> observableListEmprestimoPorUsuario;
	private EmprestimoService emprestimoService = new EmprestimoService();

	@FXML
	void onBtnBuscarAction(ActionEvent event) {
		carregarEmprestimo();
	}

	@FXML
	void onRenovarAction(ActionEvent event) {

	}

	public void carregarEmprestimo() {
		listEmprestimoPorUsuario.clear();
		listEmprestimos.clear();
		try {
			setValueColumn();
			listEmprestimoPorUsuario = emprestimoService.listAllFindByIdUser(123456);
			for (Emprestimo e : listEmprestimoPorUsuario) {
				Emprestimos emprestimos = new Emprestimos();
				emprestimos.setUsuarioId(e.getUsuario().getCpf());
				emprestimos.setLivroId(e.getLivro().getIsbn());
				emprestimos.setDataIncial(e.getData());
				emprestimos.setId(e.getId());
				emprestimos.setExemplar(e.getExemplar());
				emprestimos.setTitulo(e.getTitulo());
				if (e.getStatus() == true) {
					emprestimos.setStatus("Ativo");
				} else {
					emprestimos.setStatus("Inativo");
				}
				listEmprestimos.add(emprestimos);
			}
			observableListEmprestimoPorUsuario = FXCollections.observableArrayList(listEmprestimos);
			tblEmprestimo.setItems(observableListEmprestimoPorUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setValueColumn() {
		columUsuarioId.setCellValueFactory(new PropertyValueFactory<>("usuarioId"));
		columLivroId.setCellValueFactory(new PropertyValueFactory<>("livroId"));
		columDatInit.setCellValueFactory(new PropertyValueFactory<>("data"));
		columTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		columDatFinal.setCellValueFactory(new PropertyValueFactory<>("data"));
		columExemplar.setCellValueFactory(new PropertyValueFactory<>("exemplar"));
		columDevolvido.setCellValueFactory(new PropertyValueFactory<>("data"));
		columStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
	}
}
