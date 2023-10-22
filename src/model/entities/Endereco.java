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
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String rua;
	private String numCasa;
	private String bairro;
	private String complemento;
	private String Cidade;

}
