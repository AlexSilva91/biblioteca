package testes.emprestimo;

import model.entities.Emprestimo;
import model.services.EmprestimoService;

public class EmprestimoFindByIdUser {

	public static void main(String[] args) {
		EmprestimoService service = new EmprestimoService();

		try {
			Emprestimo emprestimo = service.findByIdUser(123456);
			System.out.println("\n" + emprestimo.toString() + "\n");
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
	}

}
