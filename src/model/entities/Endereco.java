package model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Endereco {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	private String rua;
	private String numero;
	private String bairro;
	private String complemento;
	private String cidade;
	private Long idUser;

}
