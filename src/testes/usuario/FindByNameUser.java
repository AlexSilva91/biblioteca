package testes.usuario;

import model.entities.Usuario;
import model.services.UsuarioService;

public class FindByNameUser {

	public static void main(String[] args) {
		UsuarioService service = new UsuarioService();
		try {
			Usuario usuario = service.findByName("Alex");
			System.out.println("\n"+usuario.toString());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
