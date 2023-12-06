package model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Usuario {
	@Id
	private long cpf;
	private String nome;
	private boolean status;
	private long contato;
	
	public boolean getStatus() {
		return this.status;
	}
}
