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
	public List<Order> getPaidOrders(Date from, Date to) {
		System.out.println("from "+from);
		System.out.println("to "+to);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> c = cq.from(Order.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
//		predicates.add(cb.between(c.get("creationDate"), from, to));
		predicates.add(cb.equal(c.get("paid"), true));
		predicates.add(cb.greaterThanOrEqualTo(c.get("creationDate"), from));
		predicates.add(cb.lessThan(c.get("creationDate"),to));
		cq.select(c).where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Order> q = em.createQuery(cq);
//		System.out.println(q.getResultList());
		return q.getResultList();
	}

	@Override
	public List<Order> getPaidOrders(Calendar selectedPeriod) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Person> getClients(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> cq = cb.createQuery(Person.class);
		Root<Person> c = cq.from(Person.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(c.get("type"), PersonType.ROLE_CLIENT));
		cq.select(c).where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Person> q = em.createQuery(cq);
		return q.getResultList();
	}

	@Override
	public List<Order> getOrders() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> c = cq.from(Order.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(c.get("paid"), true));
		cq.select(c).where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Order> q = em.createQuery(cq);
		return q.getResultList();
	}
}
