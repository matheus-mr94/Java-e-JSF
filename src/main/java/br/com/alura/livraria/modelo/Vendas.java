package br.com.alura.livraria.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vendas {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Livro livro;
	private Integer quantidade;
	
	public Vendas() {
		
	}

	public Vendas(Livro livro, Integer quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
	}
	
	public Livro getLivro() {
		return livro;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	
}
