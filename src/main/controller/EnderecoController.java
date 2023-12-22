package main.controller;

import model.entities.Endereco;
import model.services.EnderecoService;

public class EnderecoController {
	private EnderecoService service = new EnderecoService();
	private Endereco endereco = new Endereco();

	public Endereco getEnderecoFindByIdUser(Long idUser) {
		try {
			this.endereco = service.findByidUser(idUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.endereco;
	}

	public void saveEndereco(Endereco endArg) {
		try {
			this.endereco = this.service.saveEndereco(endArg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateEndereco(Endereco endereco) {
		try {
			this.endereco = this.getEnderecoFindByIdUser(endereco.getIdUser());
			if (this.endereco != null) {
				this.endereco = this.service.updateEndereco(endereco);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean enderecoExist(Long idUser) {
		boolean enderecoExiste = false;
		try {
			this.endereco = this.service.findByidUser(idUser);
			if (endereco != null) {
				enderecoExiste = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enderecoExiste;
	}
}
