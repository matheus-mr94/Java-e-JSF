package br.com.alura.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.alura.livraria.dao.LivroDao;
import br.com.alura.livraria.modelo.Livro;
import br.com.alura.livraria.modelo.Vendas;

@Named
@ViewScoped
public class VendasBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	LivroDao livroDao;

	public BarChartModel getVendasModel() {

		BarChartModel model = new BarChartModel();
		model.setAnimate(true);

		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2021");

		List<Vendas> vendas = getVendas(1234);

		for (Vendas venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		model.addSeries(vendaSerie);

		ChartSeries vendaSerie2015 = new ChartSeries();
		vendaSerie2015.setLabel("Vendas 2020");

		vendas = getVendas(4321);

		for (Vendas venda : vendas) {
			vendaSerie2015.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}

		model.addSeries(vendaSerie2015);

		return model;
	}

	public List<Vendas> getVendas(long seed) {

		List<Livro> livros = livroDao.listaTodos();
		List<Vendas> vendas = new ArrayList<Vendas>();

		Random random = new Random(seed);

		for (Livro livro : livros) {
			Integer quantidade = random.nextInt(500);
			vendas.add(new Vendas(livro, quantidade));
		}

		return vendas;
	}
}
