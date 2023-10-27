package model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity
public class Endereco {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String rua;
	private String numero;
	private String bairro;
	private String complemento;
	private String cidade;

}
