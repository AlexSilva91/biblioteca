package model.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Emprestimo {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	private long usuarioId;
	private long livroId;
	private LocalDate dataIncial;
	private LocalDate dataDevolucao;
	private String titulo;
	private long exemplar;
	private boolean status;

	public boolean getStatus() {
		return status;
	}

}
