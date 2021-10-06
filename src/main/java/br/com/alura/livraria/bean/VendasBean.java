package br.com.alura.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.BarChartModel;

import br.com.alura.livraria.dao.VendasDao;
import br.com.alura.livraria.modelo.Vendas;

@Named
@ViewScoped
public class VendasBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	VendasDao vendasDao;
	
	List<Vendas> vendas;

	public BarChartModel getVendasModel() {
		
		BarChartModel model = new BarChartModel();
		model.setAnimate(true);

		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2021");

		 vendas = getVendas();

		for (Vendas venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		model.addSeries(vendaSerie);


		return model;
	}

	public List<Vendas> getVendas() {
		this.vendas = vendasDao.listaTodos();
		return vendas;
		
	}
}
