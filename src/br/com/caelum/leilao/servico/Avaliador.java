package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private List<Lance> maiores;
	
	public void avalia(Leilao leilao) {
		if(leilao.getLances().size() == 0) {
			throw new RuntimeException("Não é possível avaliar um leilao sem lances!");
		}
		
		maiorDeTodos = leilao.getLances().stream().max(Comparator.comparing(lan -> lan.getValor())).get().getValor();
		menorDeTodos = leilao.getLances().stream().min(Comparator.comparing(lan -> lan.getValor())).get().getValor();
		maiores = new ArrayList<Lance>(leilao.getLances());
		maiores.sort((lance1, lance2) -> {
			if(lance1.getValor() < lance2.getValor()) {
				return 1;
			} else if (lance1.getValor() > lance2.getValor()) {
				return -1;
			} else return 0;
		});
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}
	
	public double getMaiorLance() {
		return maiorDeTodos;
	}
	
	public double getMenorLance() {
		return menorDeTodos;
	}
	
	public List<Lance> getTresMaiores() {
		return maiores;
	}
	
}
