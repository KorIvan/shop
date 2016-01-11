package com.tsystems.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystems.model.Order;
import com.tsystems.model.OrderItem;
import com.tsystems.model.Person;
import com.tsystems.model.PersonType;
import com.tsystems.model.Product;
import com.tsystems.model.Statistics;
import com.tsystems.repository.OrderRepository;
import com.tsystems.repository.PersonRepository;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PersonRepository personRepository;

	@Override
	public Statistics gatherStatistics() {
		return null;
	}

	@Override
	public Float gatherTotalIncome() {
		List<Order> paidOrders = orderRepository.getPaidOrders();
		float total = 0;
		for (Order o : paidOrders) {
			total += o.getCost();
		}
		return new Float(total);
	}

	@Override
	public Map<Product, Long> getTop10Products(int topSize) {

		List<Order> paidOrders = orderRepository.getPaidOrders();
		Map<Product, Long> top = new HashMap<>();
		for (Order o : paidOrders) {
			for (OrderItem oi : o.getOrderItems()) {
				if (top.containsKey(oi.getProduct()))
					top.put(oi.getProduct(), top.get(oi.getProduct()) + oi.getAmount());
				else
					top.put(oi.getProduct(), new Long(oi.getAmount()));
			}
		}
		Map<Product, Long> result = new LinkedHashMap<>();
		int i = 0;
		for (Map.Entry<Product, Long> e : entriesSortedByValues(top)) {
			if (i >= topSize)
				break;
			result.put(e.getKey(), e.getValue());
			i++;
		}
		return result;
	}

	@Override
	public Map<Float, Person> getTop10Clients(int topSize) {
		List<Person> clients = personRepository.getPersons(PersonType.ROLE_CLIENT);
		Map<Float, Person> top = new TreeMap<>(Collections.reverseOrder());
		for (Person p : clients) {
			float clientTotal = 0;
			if (!p.getOrders().isEmpty()) {
				for (Order o : p.getOrders()) {
					if (o.getPaid())
						clientTotal += o.getCost();
				}
				if (clientTotal > 0)
					top.put(clientTotal, p);
			}
		}
		Iterator<Map.Entry<Float, Person>> it = top.entrySet().iterator();
		for (int i = 0; i < topSize; i++)
			if (it.hasNext())
				it.next();
			else
				break;
		for (int i = 0; top.size() > topSize; i++) {
			if (it.hasNext()) {
				it.next();
				it.remove();
			} else
				break;
		}
		for (Map.Entry<Float, Person> e : top.entrySet())
			System.out.println(e.getKey());
		return top;
	}

	@Override
	public Float calculateWeekIncome(Calendar week) {
		week.set(Calendar.DAY_OF_WEEK, week.getFirstDayOfWeek());
		System.out.println(week.getFirstDayOfWeek() + " is first day of week!");
		Date from = new Date(week.getTime().getTime());
		week.add(Calendar.WEEK_OF_YEAR, 1);
		Date to = new Date(week.getTime().getTime());
		List<Order> paidOrders = orderRepository.getPaidOrders(from, to);
		float total = 0;
		for (Order o : paidOrders) {
			total += o.getCost();
		}
		return new Float(total);
	}

	@Override
	public Float calculateWeekIncome(Date from, Date to) {
		List<Order> paidOrders = orderRepository.getPaidOrders(from, to);
		float total = 0;
		for (Order o : paidOrders) {
			total += o.getCost();
		}
		return new Float(total);
	}

	private Float calculateMonthIncome() {
		return 0f;
	}

	private Float calculateTotalIncome() {
		return 0f;
	}

	private static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(
			Map<K, V> map) {
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				int res = e2.getValue().compareTo(e1.getValue());
				return res != 0 ? res : 1;
			}
		});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}
}
