package testes.emprestimo;

import model.entities.Emprestimo;
import model.entities.Livros;
import model.entities.Usuario;
import model.services.EmprestimoService;
import model.services.LivroService;
import model.services.UsuarioService;

public class EmprestimoSave {

	public static void main(String[] args) {
		EmprestimoService service = new EmprestimoService();
		UsuarioService usuarioService = new UsuarioService();
		LivroService livroService = new LivroService();
		Emprestimo emprestimo = new Emprestimo();

		try {
			Livros livro = livroService.findById(1234);
			Usuario usuario = usuarioService.findById(123456);

			emprestimo.setUsuario(usuario);
			emprestimo.setLivro(livro);
			emprestimo.setNumExemplar(1);
			livro.setNumExemplares(0);
			livro.setStatus(false);
			emprestimo.setTitulo(livro.getTitulo());

			livro = livroService.updateLivro(livro);
			emprestimo = service.saveEmprestimo(emprestimo);

			System.out.println("\n" + livro.toString() + "\n");
			System.out.println("\n" + emprestimo.toString() + "\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
