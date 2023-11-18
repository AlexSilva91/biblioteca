package model.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Emprestimo {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Livros livro;
	private LocalDate data;
	private String titulo;
	private long exemplar;
	private boolean status;

	public Boolean getStatus() {
		return status;
	}
}
