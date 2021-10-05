package br.com.alura.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.livraria.dao.AutorDao;
import br.com.alura.livraria.modelo.Autor;

@Named
@ViewScoped
public class AutorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	private Integer autorId;

	@Inject
	AutorDao dao;

	public Autor getAutor() {
		return autor;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void carregarAutorPeloId() {
		this.autor = this.dao.buscaPorId(autorId);
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			this.dao.adiciona(this.autor);
		} else {
			this.dao.atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}

	public List<Autor> getAutores() {
		return this.dao.listaTodos();
	}

	public void remover(Autor autor) {
		this.dao.remove(autor);
	}

	public void carregar(Autor autor) {
		this.autor = autor;
	}

}
