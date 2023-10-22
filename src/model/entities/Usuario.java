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
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer cpf;
	private String nome;
	private Endereco endereco;
	private boolean status;
	private Integer contato;
	
}
