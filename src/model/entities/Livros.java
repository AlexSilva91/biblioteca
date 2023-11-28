package model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

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
