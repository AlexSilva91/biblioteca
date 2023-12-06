package gui.emprestimo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.controller.EmprestimoControllerMain;
import model.entities.Emprestimo;
import model.entities.Emprestimos;
import model.services.EmprestimoService;

public class RenovarController implements Initializable {

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
	private TextField txtBusca;
	@FXML
	private TableView<Emprestimos> tblEmprestimo;
	private List<Emprestimo> listEmprestimoPorUsuario = new ArrayList<Emprestimo>();
	private List<Emprestimos> listEmprestimos = new ArrayList<Emprestimos>();
	private ObservableList<Emprestimos> observableListEmprestimoPorUsuario;
	private EmprestimoService emprestimoService = new EmprestimoService();
	private EmprestimoControllerMain controllerMain = new EmprestimoControllerMain();

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
			listEmprestimoPorUsuario = this.controllerMain.listAllFindByIdUser(Long.parseLong(this.txtBusca.getText()));
			for (Emprestimo e : listEmprestimoPorUsuario) {
				Emprestimos emprestimos = new Emprestimos();
				emprestimos.setId(e.getId());
				emprestimos.setUsuarioId(e.getUsuarioId());
				emprestimos.setLivroId(e.getLivroId());
				if (e.getDt_Incial() != null) {
					String dt = EmprestimoControllerMain.ValidData(e.getDt_Incial());
					emprestimos.setDt_Incial(dt);
					System.out.println("\nData inicial" + dt);
				}
				if (e.getDt_Final() != null) {
					String dt = EmprestimoControllerMain.ValidData(e.getDt_Final());
					emprestimos.setDt_Final(dt);
					System.out.println("\nData final: " + dt);
				}
				if (e.getDt_Devolucao() != null) {
					String dt = EmprestimoControllerMain.ValidData(e.getDt_Devolucao());
					emprestimos.setDt_Devolucao(dt);
					System.out.println("\nData devolução" + dt);
				}
				emprestimos.setTitulo(e.getTitulo());
				emprestimos.setExemplar(e.getExemplar());
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
		columLivroId.setCellValueFactory(new PropertyValueFactory<>("livroId"));
		columDatInit.setCellValueFactory(new PropertyValueFactory<>("dt_Incial"));
		columTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		columDatFinal.setCellValueFactory(new PropertyValueFactory<>("dt_Devolucao"));
		columExemplar.setCellValueFactory(new PropertyValueFactory<>("exemplar"));
		columDevolvido.setCellValueFactory(new PropertyValueFactory<>("dt_Final"));
		columStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldMaxLength(this.txtBusca, 11);
		Constraints.setTextFieldInterger(this.txtBusca);

	}
}
