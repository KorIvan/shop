package com.tsystems.model;

import java.util.Map;

public class Statistics {
	private Float incomeTotal;
	private Float incomePerMonth;
	private Float incomePerWeek;
	private Map<Integer, Product> top10Products;
	private Map<Integer, Person> top10Clients;
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
	public Map<Integer, Product> getTop10Products() {
		return top10Products;
	}
	public void setTop10Products(Map<Integer, Product> top10Products) {
		this.top10Products = top10Products;
	}
	public Map<Integer, Person> getTop10Clients() {
		return top10Clients;
	}
	public void setTop10Clients(Map<Integer, Person> top10Clients) {
		this.top10Clients = top10Clients;
	}

}
