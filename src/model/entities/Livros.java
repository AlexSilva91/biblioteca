package model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Livros {
	@Id
	private long isbn;
	private String titulo;
	private String autor;
	private Integer ano;
	private long exemplar;
	private boolean status;

	public boolean getStatus() {
		return this.status;
	}
}
