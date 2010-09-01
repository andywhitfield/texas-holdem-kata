package kata.holdem.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Iterables {
	public static <T, K> Map<K, Collection<T>> groupBy(Iterable<T> items, Action<T, K> keyMapper) {
		Map<K, Collection<T>> grouped = new HashMap<K, Collection<T>>();
		
		for (T item: items) {
			K key = keyMapper.action(item);
			Collection<T> groupedValues = grouped.get(key);
			if (groupedValues == null) {
				groupedValues = new ArrayList<T>();
				grouped.put(key, groupedValues);
			}
			groupedValues.add(item);
		}
		
		return grouped;
	}

	public static <T, V> Collection<V> select(Iterable<T> items, Action<T, V> selector) {
		List<V> selected = new ArrayList<V>();
		for (T item : items) selected.add(selector.action(item));
		return selected;
	}

	public static <T> Collection<T> where(Iterable<T> items, Predicate<T> filterWherePredicate) {
		List<T> selected = new ArrayList<T>();
		for (T item : items) if (filterWherePredicate.evaluate(item)) selected.add(item);
		return selected;
	}
}
