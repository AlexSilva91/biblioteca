package testes.usuario;

import java.util.List;

import model.entities.Usuario;
import model.services.UsuarioService;

public class FindByCity {

	public static void main(String[] args) {
		UsuarioService service = new UsuarioService();
		try {

			List<Usuario> list = service.listAllFindByCity("Exu");
			for(Usuario usuario : list) {
				System.out.println("\n" + usuario.toString());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
