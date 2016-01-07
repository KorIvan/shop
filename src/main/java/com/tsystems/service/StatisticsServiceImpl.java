package com.tsystems.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystems.model.Order;
import com.tsystems.model.Person;
import com.tsystems.model.Product;
import com.tsystems.model.Statistics;
import com.tsystems.repository.StatisticsRepository;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	private StatisticsRepository statisticsRepository;

	@Override
	public Statistics gatherStatistics() {
		return null;
	}

	@Override
	public Map<Integer, Product> getTop10Products(int topSize) {
		
		List<Order> paidOrders = statisticsRepository.getOrders();

		return null;
	}

	@Override
	public Map<Float, Person> getTop10Clients(int topSize) {
		List<Person> clients = statisticsRepository.getClients();
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
		List<Order> paidOrders = statisticsRepository.getPaidOrders(from, to);
		float total = 0;
		for (Order o : paidOrders) {
			total += o.getCost();
		}
		return new Float(total);
	}

	@Override
	public Float calculateWeekIncome(Date from, Date to) {
		List<Order> paidOrders = statisticsRepository.getPaidOrders(from, to);
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
}
