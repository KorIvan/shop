package com.tsystems.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.tsystems.model.Order;
import com.tsystems.model.Person;
import com.tsystems.model.PersonType;
import com.tsystems.model.Product;
import com.tsystems.model.Statistics;

public interface StatisticsService {

	Float calculateWeekIncome(Date from, Date to);

	Float calculateWeekIncome(Calendar week);

	Map<Product, Long> getTop10Products(int topSize);

	Statistics gatherStatistics();

	Map<Float, Person> getTop10Clients(int topSize);

}
