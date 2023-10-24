package testes.emprestimo;

import model.entities.Emprestimo;
import model.services.EmprestimoService;

public class EmprestimoUpdate {

	public static void main(String[] args) {
		EmprestimoService service = new EmprestimoService();

		try {
			Emprestimo emprestimo = service.findById(3);
			emprestimo.setNumExemplar(8);
			emprestimo = service.updateEmprestimo(emprestimo);
			System.out.println("\n" + emprestimo.toString() + "\n");
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
	}

}
