package br.com.alura.livraria.modelo;

public class Vendas {
	
	private Livro livro;
	private Integer quantidade;
	
	
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
