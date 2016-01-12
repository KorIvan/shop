package com.tsystems.model;

import java.util.Map;

public class Statistics {
	private Float incomeTotal;
	private Float incomePerMonth;
	private Float incomePerWeek;
	private Map<Product, Long> topProducts;
	private Map<Float, Person> topClients;
	public Float getIncomeTotal() {
		return incomeTotal;
	}
	public void setIncomeTotal(Float incomeTotal) {
		this.incomeTotal = incomeTotal;
	}
	public Float getIncomePerMonth() {
		return incomePerMonth;
	}
	public void setIncomePerMonth(Float incomePerMonth) {
		this.incomePerMonth = incomePerMonth;
	}
	public Float getIncomePerWeek() {
		return incomePerWeek;
	}
	public void setIncomePerWeek(Float incomePerWeek) {
		this.incomePerWeek = incomePerWeek;
	}
	public Map<Product, Long> getTopProducts() {
		return topProducts;
	}
	public Map<Float, Person> getTopClients() {
		return topClients;
	}
	public void setTopClients(Map<Float, Person> map) {
		this.topClients = map;
	}
	public void setTopProducts(Map<Product, Long> top10Products) {
		this.topProducts = top10Products;
		
	}

}
