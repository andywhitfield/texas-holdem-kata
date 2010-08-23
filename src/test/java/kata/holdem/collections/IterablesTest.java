package kata.holdem.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class IterablesTest {
	@Test
	public void given_two_elements_with_the_same_key_should_create_a_map_with_one_key_and_two_items_in_the_list_of_values() {
		List<Integer> ints = Arrays.asList(1, 1);
		Map<Integer, Collection<Integer>> grouped = Iterables.groupBy(ints, new Action<Integer, Integer>() {
			@Override
			public Integer action(Integer input) {
				return input;
			}});
		Assert.assertEquals(1, grouped.size());
		Assert.assertEquals(2, grouped.get(1).size());
		Assert.assertEquals(Integer.valueOf(1), index(grouped.get(1), 0));
		Assert.assertEquals(Integer.valueOf(1), index(grouped.get(1), 1));
	}

	@Test
	public void given_two_elements_with_different_keys_should_create_a_map_with_two_keys_and_one_item_in_the_list_of_each_value() {
		List<Integer> ints = Arrays.asList(1, 9);
		Map<Integer, Collection<Integer>> grouped = Iterables.groupBy(ints, new Action<Integer, Integer>() {
			@Override
			public Integer action(Integer input) {
				return input;
			}});
		Assert.assertEquals(2, grouped.size());
		Assert.assertEquals(1, grouped.get(1).size());
		Assert.assertEquals(Integer.valueOf(1), index(grouped.get(1), 0));
		Assert.assertEquals(Integer.valueOf(9), index(grouped.get(9), 0));
	}
	
	@Test
	public void given_two_ints_should_map_to_strings() {
		Collection<String> items = Iterables.select(Arrays.asList(1, 2, 3), new Action<Integer, String>() {
			@Override
			public String action(Integer input) {
				return input.toString();
			}});
		Assert.assertEquals("1", index(items, 0));
		Assert.assertEquals("2", index(items, 1));
		Assert.assertEquals("3", index(items, 2));
	}
	
	private <T> T index(Collection<T> items, int index) {
		if (items instanceof List) {
			List<T> list = (List<T>) items;
			return list.get(index);
		}
		int count = 0;
		for (T item : items) {
			if (count == index) return item;
			count++;
		}
		return null;
	}
}
