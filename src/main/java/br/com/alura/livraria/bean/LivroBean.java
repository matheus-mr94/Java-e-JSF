package br.com.alura.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {
	
	private Livro livro = new Livro();
	private Integer autorId;
	

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public List<Autor> getAutores(){
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}
   
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
		this.livro.adicionaAutor(autor);
	}
	
	public void gravar() {
        System.out.println("Gravando livro " + this.livro.getTitulo());
        
        if(livro.getAutores().isEmpty()) {
        	throw new RuntimeException("Livro deve ter pelo menos um Autor");
        }
        
        new DAO<Livro>(Livro.class).adiciona(this.livro);
    }

}
