package gui.emprestimo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.controller.EmprestimoControllerMain;
import model.entities.Emprestimo;
import model.entities.Emprestimos;

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
	private EmprestimoControllerMain controllerMain = new EmprestimoControllerMain();
	private Emprestimos emprestimos = new Emprestimos();

	@FXML
	void onBtnBuscarAction(ActionEvent event) {
		limparListas();
		try {
			if (validBuscaPreenchida()) {
				listEmprestimoPorUsuario = buscaEmprestimosPorUsuario();
				if (!this.listEmprestimoPorUsuario.isEmpty()) {
					carregarEmprestimo();
				} else {
					Alerts.showAlert("Error!", " Este usuário não existe\n Ou não possui empréstimos!", null,
							AlertType.ERROR);
					limparListas();
				}
			} else {
				Alerts.showAlert("Error!", "Necessário informar o CPF!", null, AlertType.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onRenovarAction(ActionEvent event) {
		if (!tblEmprestimo.getItems().isEmpty()) {
			if (this.emprestimos != null) {
				if (this.emprestimos.getStatus().equals("ativo")) {
					Emprestimo emprestimo = new Emprestimo();
					emprestimo = this.controllerMain.findById(this.emprestimos.getId());
					if (emprestimo != null) {
						emprestimo.setDt_Final(EmprestimoControllerMain.convertStringEmLocalDate(
								EmprestimoControllerMain.validDataDevolucao(EmprestimoControllerMain
										.convertStringEmLocalDate(this.emprestimos.getDt_Final()))));
						this.controllerMain.updateEmprestimo(emprestimo);
						Alerts.showAlert("Renovado!",
								"Emprestimo renovado por mais 7 dias!\nNova data de devolução: "
										+ EmprestimoControllerMain.validDataDevolucao(EmprestimoControllerMain
												.convertStringEmLocalDate(this.emprestimos.getDt_Final())),
								null, AlertType.INFORMATION);
						atualizarTabela();
					}
				} else {
					Alerts.showAlert("Error!", "Reseva já inativa!", null, AlertType.ERROR);
				}
			} else {
				Alerts.showAlert("Error!", "Necessário selecionar reserva!", null, AlertType.ERROR);
			}
		} else {
			Alerts.showAlert("Error!", "Impossível renovar emprestimo!", null, AlertType.ERROR);
		}
	}

	public List<Emprestimo> buscaEmprestimosPorUsuario() {
		this.listEmprestimoPorUsuario = this.controllerMain
				.listAllFindByIdUser(Long.parseLong(this.txtBusca.getText()));
		return this.listEmprestimoPorUsuario;
	}

	public void atualizarTabela() {
		limparListas();
		this.listEmprestimoPorUsuario = buscaEmprestimosPorUsuario();
		carregarEmprestimo();
	}

	public void limparListas() {
		listEmprestimoPorUsuario.clear();
		listEmprestimos.clear();
		tblEmprestimo.getItems().clear();
	}

	public boolean validBuscaPreenchida() {
		boolean valid = false;
		if (!this.txtBusca.getText().isEmpty()) {
			valid = true;
		}
		return valid;
	}

	public void carregarEmprestimo() {
		try {
			setValueColumn();
			for (Emprestimo e : listEmprestimoPorUsuario) {
				Emprestimos emprestimos = new Emprestimos();
				emprestimos.setId(e.getId());
				emprestimos.setUsuarioId(e.getUsuarioId());
				emprestimos.setLivroId(e.getLivroId());
				if (e.getDt_Incial() != null) {
					String dt = EmprestimoControllerMain.ValidData(e.getDt_Incial());
					emprestimos.setDt_Incial(dt);
				}
				if (e.getDt_Final() != null) {
					String dt = EmprestimoControllerMain.ValidData(e.getDt_Final());
					emprestimos.setDt_Final(dt);
				}
				if (e.getDt_Devolucao() != null) {
					String dt = EmprestimoControllerMain.ValidData(e.getDt_Devolucao());
					emprestimos.setDt_Devolucao(dt);
				}
				emprestimos.setTitulo(e.getTitulo());
				emprestimos.setExemplar(e.getExemplar());
				if (e.getStatus() == true) {
					emprestimos.setStatus("ativo");
				} else {
					emprestimos.setStatus("inativo");
				}
				listEmprestimos.add(emprestimos);
			}
			observableListEmprestimoPorUsuario = FXCollections.observableArrayList(listEmprestimos);
			tblEmprestimo.setItems(observableListEmprestimoPorUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void capturaReserva() {
		// Capturar o elemento selecionado quando houver uma alteração na seleção
		this.tblEmprestimo.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				this.emprestimos = newSelection;
			}

		});
	}

	public void setValueColumn() {
		columLivroId.setCellValueFactory(new PropertyValueFactory<>("livroId"));
		columDatInit.setCellValueFactory(new PropertyValueFactory<>("dt_Incial"));
		columTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		columDatFinal.setCellValueFactory(new PropertyValueFactory<>("dt_Final"));
		columExemplar.setCellValueFactory(new PropertyValueFactory<>("exemplar"));
		columDevolvido.setCellValueFactory(new PropertyValueFactory<>("dt_Devolucao"));
		columStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Constraints.setTextFieldMaxLength(this.txtBusca, 11);
		Constraints.setTextFieldInterger(this.txtBusca);

		capturaReserva();
	}
}
