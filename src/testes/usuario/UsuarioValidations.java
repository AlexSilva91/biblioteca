package testes.usuario;

import main.validations.UsuarioValidation;

public class UsuarioValidations {

	public static void main(String[] args) {
		usuarioExiste();
		
	}

	public static void usuarioExiste() {
		boolean existe;
		try {
			UsuarioValidation validation = new UsuarioValidation();
			existe = validation.usuarioExiste(123456);
			System.out.println(existe);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}