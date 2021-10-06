package br.com.alura.livraria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.livraria.dao.AutorDao;
import br.com.alura.livraria.dao.LivroDao;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.tx.Transacional;

@Named
@ViewScoped
public class LivroBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;
	private List<Livro> livros;
	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação");
	
	@Inject
	LivroDao livroDao;
	
	@Inject
	AutorDao autorDao;
	
	

	public List<String> getGeneros() {
	    return generos;
	}
	
	
	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public List<Livro> getLivros(){
		
		if(this.livros == null) {
		this.livros = this.livroDao.listaTodos();
		}
		return livros;
	}
	
	public List<Autor> getAutores(){
		return autorDao.listaTodos();
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}
	
	public void carregarLivroPeloId() {
		this.livro = this.livroDao.buscaPorId(livroId);
	}
   
	public void gravarAutor() {
		Autor autor = autorDao.buscaPorId(autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Escrito por: " + autor.getNome());
	}
	
	@Transacional
	public void gravar() {
	    System.out.println("Gravando livro " + this.livro.getTitulo());

	    if (livro.getAutores().isEmpty()) {
	        FacesContext.getCurrentInstance().addMessage("autor",
	                new FacesMessage("Livro deve ter pelo menos um Autor."));
	        return;
	    }


	    if(this.livro.getId() == null) {
	        this.livroDao.adiciona(this.livro);

	        this.livros = livroDao.listaTodos();
	    } else {
	        this.livroDao.atualiza(this.livro);
	    }

	    this.livro = new Livro();
	}
	
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}
	
	public void carregar(Livro livro) {
		this.livro = livro;
	}
	
	@Transacional
	public void remover(Livro livro) {
		System.out.println("Removendo livro");
		 this.livroDao.remove(livro);
	}
	
	public String formAutor() {
		System.out.println("Chamando o formulário de autor");
		return "autor?faces-redirect=true";
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException{
		String valor = value.toString();
		if(!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN deve começar com 1"));
		}
	}
}
