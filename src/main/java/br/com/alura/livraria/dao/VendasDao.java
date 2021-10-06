package br.com.alura.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.alura.livraria.modelo.Vendas;

public class VendasDao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private DAO<Vendas> dao;
	
	@Inject
	EntityManager em;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Vendas>(this.em,Vendas.class);
	}

	public List<Vendas> listaTodos() {
		return dao.listaTodos();
	}

	
	

}
