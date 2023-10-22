package model.entities;

import java.io.Serializable;

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
public class Livros implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer isbn;
	private String titulo;
	private String autor;
	private Integer lacamento;
	private Integer NumExemplares;
	private boolean status;
}
