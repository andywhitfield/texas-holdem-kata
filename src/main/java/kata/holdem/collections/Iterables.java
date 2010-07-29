package kata.holdem.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Iterables {
	public static <T, K> Map<K, List<T>> groupBy(Iterable<T> items, Action<T, K> keyMapper) {
		Map<K, List<T>> grouped = new HashMap<K, List<T>>();
		
		for (T item: items) {
			K key = keyMapper.action(item);
			List<T> groupedValues = grouped.get(key);
			if (groupedValues == null) {
				groupedValues = new ArrayList<T>();
				grouped.put(key, groupedValues);
			}
			groupedValues.add(item);
		}
		
		return grouped;
	}
}
