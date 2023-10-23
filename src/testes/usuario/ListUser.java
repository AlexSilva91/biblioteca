package testes.usuario;

import java.util.List;

import model.entities.Usuario;
import model.services.UsuarioService;

public class ListUser {

	public static void main(String[] args) {
		UsuarioService service = new UsuarioService();
		try {
			List<Usuario> listUser = service.listUser();
			for (Usuario usuario : listUser) {
				System.out.println("\n" + usuario.toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
