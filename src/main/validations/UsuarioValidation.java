package main.validations;

import java.util.ArrayList;
import java.util.List;

import model.entities.Usuario;
import model.services.UsuarioService;

public class UsuarioValidation {
	private static final UsuarioService service = new UsuarioService();
	private static List<Usuario> listUsuarios = new ArrayList<>();
	private static Usuario usuario = new Usuario();

	public Boolean usuarioExiste(Integer id) {
		boolean existe = false;
		try {
			usuario = service.findById(id);
			/**
			 * Se id for diferente de nulo significa que o usuário já existe
			 * logo irá retornar true
			 */
			if (usuario != null){
				existe = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return existe;
	}
}
