package model.entities;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Emprestimos {

	private long id;
	private long usuarioId;
	private long livroId;
	private LocalDate dataIncial;
	private LocalDate dataFinal;
	private LocalDate dataDevolucao;
	private String titulo;
	private long exemplar;
	private String status;
}
