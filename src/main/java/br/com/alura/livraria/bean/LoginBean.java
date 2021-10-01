package br.com.alura.livraria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.alura.livraria.dao.UsuarioDao;
import br.com.alura.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {
	
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String logar() {
		
		boolean existe = new UsuarioDao().existe(this.usuario);
		if(existe) {
			
		return "livro?faces-redirect=true";
		}
		
		return null;
		}
		
	}


