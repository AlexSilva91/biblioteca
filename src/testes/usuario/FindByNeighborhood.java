package testes.usuario;

import java.util.List;

import model.entities.Usuario;
import model.services.UsuarioService;

public class FindByNeighborhood {

	public static void main(String[] args) {
		UsuarioService service = new UsuarioService();
		try {
			List<Usuario> list = service.listAllFindByNeighborhood("Centro");
			for(Usuario usuario : list) {
				System.out.println("\n" + usuario.toString());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
