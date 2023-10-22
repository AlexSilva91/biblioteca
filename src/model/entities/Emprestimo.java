package model.entities;

import java.io.Serializable;

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
public class Emprestimo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Usuario usuario;
	private Livros livro;
	private String titulo;
	private Integer numExemplar;
	
}
