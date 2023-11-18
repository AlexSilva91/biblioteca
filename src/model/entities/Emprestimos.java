package model.entities;

import java.time.LocalDate;

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
