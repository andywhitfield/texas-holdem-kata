package kata.holdem.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
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

	public static <T> List<T> sort(Iterable<T> collection, Comparator<T> sortBy) {
		List<T> sorted = new ArrayList<T>();
		for (T t : collection) sorted.add(t);
		Collections.sort(sorted, sortBy);
		return sorted;
	}

	public static <T> Iterable<T> join(final Iterable<T> iterable1, final Iterable<T> iterable2) {
		return new Iterable<T>() {
			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					private boolean usingFirstIterator = true;
					private Iterator<T> iterator1 = iterable1.iterator();
					private Iterator<T> iterator2 = iterable2.iterator();
					
					@Override
					public boolean hasNext() {
						if (usingFirstIterator) {
							boolean hasNext = iterator1.hasNext();
							if (hasNext) return true;
							usingFirstIterator = false;
						}
						return iterator2.hasNext();
					}

					@Override
					public T next() {
						return usingFirstIterator ? iterator1.next() : iterator2.next();
					}

					@Override
					public void remove() {
						if (usingFirstIterator) iterator1.remove();
						else iterator2.remove();
					}
				};
			}
		};
	}
}
