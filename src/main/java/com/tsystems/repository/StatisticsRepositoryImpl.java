package com.tsystems.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.tsystems.model.IncomePeriod;
import com.tsystems.model.Order;
import com.tsystems.model.Person;
import com.tsystems.model.PersonType;

@Repository("statisticsRepository")
public class StatisticsRepositoryImpl implements StatisticsRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Order> getPaidOrders(IncomePeriod period) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<Order> getPaidOrders(Calendar selectedPeriod) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
