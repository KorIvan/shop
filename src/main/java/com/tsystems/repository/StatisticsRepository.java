package com.tsystems.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.tsystems.model.IncomePeriod;
import com.tsystems.model.Order;
import com.tsystems.model.Person;
import com.tsystems.model.PersonType;
public interface StatisticsRepository {
	List<Order> getPaidOrders(IncomePeriod period);
	List<Order> getPaidOrders(Calendar selectedPeriod);
	List<Order> getPaidOrders(Date from, Date to);
	List<Person> getClients();
	List<Order> getOrders();
}
