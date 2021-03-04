package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
public class Carrinho {
	
	private List<Ingresso> ingressos = new ArrayList<>();	
	
	
	public List<Ingresso> getIngressos() {
		return ingressos;
	}
	
	public Compra toCompra() {
		return new Compra(ingressos); 
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public void add(Ingresso ingresso) {
		ingressos.add(ingresso); 	
	}
	
	public void add (List<Ingresso>ingressos) { 
		this.ingressos.addAll(ingressos); 
	}
	public void remove(Ingresso ingresso) {
		this.ingressos.remove(ingresso); 
	}
	public void limpa() {
		this.ingressos.clear();
	}
	
	public boolean isSelecionado(Lugar lugar) {
		return ingressos.stream().map(Ingresso::getLugar).anyMatch(lugarDoIngresso -> lugarDoIngresso.equals(lugar)); 
	}
	
	public BigDecimal getTotal() {
		return ingressos.stream().map(Ingresso::getPreco).reduce(BigDecimal::add).orElse(BigDecimal.ZERO); 
	}
}
