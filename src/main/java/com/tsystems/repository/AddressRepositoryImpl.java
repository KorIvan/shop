package com.tsystems.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tsystems.model.Address;
import com.tsystems.model.Person;

@Repository("addressRepository")
public class AddressRepositoryImpl implements AddressRepository {
	@PersistenceContext
	private EntityManager em;
	@Override
	public List<Address> findAllAddresses(Long clientId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Address> cq = cb.createQuery(Address.class);
		Root<Address> c = cq.from(Address.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(c.get("client"),  em.find(Person.class, clientId)));

		cq.select(c).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Address> q = em.createQuery(cq);
		List<Address> founded = q.getResultList();
		return founded;
	}
	@Override
	public Address findAddressById(Long id) {
		return em.find(Address.class, id);
	}
	@Transactional
	@Override
	public void updateAddress(Address address) {
		em.merge(address);
	}
	@Transactional
	@Override
	public void createAddress(Address address) {
		em.persist(address);
	}
}
