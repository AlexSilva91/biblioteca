package model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Usuario {
	@Id
	private long cpf;
	private String nome;
	@ManyToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	private boolean status;
	private long contato;
	
	public boolean getStatus() {
		return this.status;
	}
}
