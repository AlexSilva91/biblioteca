package testes.endereco;

import model.entities.Endereco;
import model.services.EnderecoService;

public class EnderecoFindById {

	public static void main(String[] args) {
		EnderecoService enderecoService = new EnderecoService();
		try {
			Endereco endereco = enderecoService.findById(1);
			System.out.println("\n" + endereco.toString().toLowerCase());

			endereco = enderecoService.findById(2);
			System.out.println("\n" + endereco.toString().toUpperCase());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
