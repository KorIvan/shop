package com.tsystems.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.model.DeliveryMethod;
import com.tsystems.model.Order;
import com.tsystems.model.OrderItem;
import com.tsystems.model.PaymentMethod;
import com.tsystems.model.Person;

@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void createOrder(Order order) {
		em.persist(order);
	}

	public List<Order> findAllOrders(Long clientId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> c = cq.from(Order.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(c.get("client"), em.find(Person.class,clientId)));

		cq.select(c).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Order> q = em.createQuery(cq);
		List<Order> founded = q.getResultList();
		return founded;
	}
	public List<Order> findAllOrders() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> c = cq.from(Order.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		cq.select(c).where(predicates.toArray(new Predicate[] {}));
		cq.orderBy(cb.desc(c.get("creationDate")));
		
		TypedQuery<Order> q = em.createQuery(cq);
		return q.getResultList();
	}

	public boolean hasUnfinishedOrder(Long clientId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> c = cq.from(Order.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(c.get("client"), em.find(Person.class,clientId)));
		predicates.add(cb.equal(c.get("deliveryMethod"), DeliveryMethod.UNKNOWN));
		predicates.add(cb.equal(c.get("payMethod"), PaymentMethod.UNKNOWN));

		cq.select(c).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Order> q = em.createQuery(cq);
		List<Order> founded = q.getResultList();
		if (founded.isEmpty())
			return false;
		else
			return true;
	}
	@Override
	@Transactional
	public void deleteOrder(Order order) {
		em.remove(order);

	}
	
	@Override
	public Order findOrderById(Long orderId) {
		return em.find(Order.class, orderId);
	}

	@Override
	@Transactional
	public void updateOrder(Order order) {
		em.merge(order);
	}
	
	@Override
	@Transactional
	public void cancelOrder(Order order) {
		em.merge(order);
	}
	public OrderItem findOrderItemById(Long id) {
		return em.find(OrderItem.class, id);
	}
	@Override
	public List<Order> getPaidOrders() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> c = cq.from(Order.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(cb.equal(c.get("paid"), true));
		cq.select(c).where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Order> q = em.createQuery(cq);
		return q.getResultList();
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
}
