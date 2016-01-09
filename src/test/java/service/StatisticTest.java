package service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import com.tsystems.model.OrderItem;
import com.tsystems.model.Product;

public class StatisticTest {

	@Test
	public void topProductTop() {
		OrderItem a = new OrderItem();
		Product a1 = new Product();
		a1.setId(1L);
		a.setProduct(a1);
		a.setAmount(14);
		Product b1 = new Product();
		b1.setId(5L);
		OrderItem b = new OrderItem();
		b.setProduct(b1);
		b.setAmount(7);
		OrderItem c = new OrderItem();
		Product c1 = new Product();
		c1.setId(3L);
		c.setProduct(c1);
		Product d1 = new Product();
		d1.setId(10L);
		// List<OrderItem> oil=new ArrayList<>();
		// oil.add(a);oil.add(b);oil.add(c);
		// for (OrderItem oi:oil){
		//
		// }
		Map<Product, Long> top = new HashMap<>();
		top.put(a1, 14L);
		top.put(b1, 7L);
		top.put(c1, 9L);
		top.put(d1, 21L);
		System.out.println(top);
		System.out.println("----------------");
//		System.out.println(entriesSortedByValues(top));
		int topSize = 3;
		Map<Product, Long> result = new HashMap<>();
		int i = 0;
		SortedSet<Entry<Product, Long>> sorted=entriesSortedByValues(top);
		for (Map.Entry<Product, Long> e : sorted) {
			if (i >= topSize)
				break;
			result.put(e.getKey(), e.getValue());
			i++;
		}
		i = 0;
		System.out.println(result);
		for (Map.Entry<Product, Long> e : entriesSortedByValues(top)) {
			if (i >= topSize)
				break;
			System.out.println(e.getKey().getId() + " " + e.getValue());
			i++;
		}
	}

	static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
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