package testes.emprestimo;

import java.util.List;

import model.entities.Emprestimo;
import model.services.EmprestimoService;

public class EmprestimoListAllFindByTitle {
	public static void main(String[] args) {
		EmprestimoService service = new EmprestimoService();

		try {
			List<Emprestimo> listAll = service.listAllFindByTitle("Código da vinci");
			for (Emprestimo emprestimo : listAll) {
				System.out.println("\n" + emprestimo.toString() + "\n");
			}
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
	}
}
