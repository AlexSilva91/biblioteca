package testes.endereco;

import model.entities.Endereco;
import model.services.EnderecoService;

public class UpdateEndereco {

	public static void main(String[] args) {
		EnderecoService enderecoService = new EnderecoService();
		
		try {
			Endereco endereco = enderecoService.findById(1);
			endereco.setCidade("Bodocó");
			endereco.setIdUser(13166456490L);
			endereco = enderecoService.updateEndereco(endereco);
			System.out.println("\n" + endereco.getCidade().toUpperCase() + "\n");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
