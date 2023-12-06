package model.entities;

import lombok.Data;

@Data
public class Emprestimos {

	private long id;
	private long usuarioId;
	private long livroId;
	private String dt_Incial;
	private String dt_Final;
	private String dt_Devolucao;
	private String titulo;
	private long exemplar;
	private String status;
}
