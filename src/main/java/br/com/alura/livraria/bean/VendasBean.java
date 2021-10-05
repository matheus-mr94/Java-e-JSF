package br.com.alura.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.charts.bar.BarChartModel;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.modelo.Vendas;

@ManagedBean
@ViewScoped
public class VendasBean {
	
	
//	public BarChartModel getVendasModel() {

//	    BarChartModel model = new BarChartModel();
//		model.setAnimate(true);
	
//	    ChartSeries vendaSerie = new ChartSeries();
//	    vendaSerie.setLabel("Vendas 2021");
//
//	    List<Vendas> vendas = getVendas(1234);
//
//	    for (Vendas venda : vendas) {
//	        vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
//	    }
//
//	    model.addSeries(vendaSerie);
//
//	    ChartSeries vendaSerie2015 = new ChartSeries();
//	    vendaSerie2015.setLabel("Vendas 2020");
//
//	    vendas = getVendas(4321);
//
//	    for (Vendas venda : vendas) {
//	        vendaSerie2015.set(venda.getLivro().getTitulo(),
//	                venda.getQuantidade());
//	    }
//
//	    model.addSeries(vendaSerie2015);
//
//	    return model;
//	}

	public List<Vendas> getVendas(long seed) {

	    List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
	    List<Vendas> vendas = new ArrayList<Vendas>();

	    Random random = new Random(seed);

	    for (Livro livro : livros) {
	        Integer quantidade = random.nextInt(500);
	        vendas.add(new Vendas(livro, quantidade));
	    }

	    return vendas;
	}
}
