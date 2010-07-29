package kata.holdem.collections;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class IterablesTest {
	@Test
	public void given_two_elements_with_the_same_key_should_create_a_map_with_one_key_and_two_items_in_the_list_of_values() {
		List<Integer> ints = Arrays.asList(1, 1);
		Map<Integer, List<Integer>> grouped = Iterables.groupBy(ints, new Action<Integer, Integer>() {
			@Override
			public Integer action(Integer input) {
				return input;
			}});
		Assert.assertEquals(1, grouped.size());
		Assert.assertEquals(2, grouped.get(1).size());
		Assert.assertEquals(Integer.valueOf(1), grouped.get(1).get(0));
		Assert.assertEquals(Integer.valueOf(1), grouped.get(1).get(1));
	}

	@Test
	public void given_two_elements_with_different_keys_should_create_a_map_with_two_keys_and_one_item_in_the_list_of_each_value() {
		List<Integer> ints = Arrays.asList(1, 9);
		Map<Integer, List<Integer>> grouped = Iterables.groupBy(ints, new Action<Integer, Integer>() {
			@Override
			public Integer action(Integer input) {
				return input;
			}});
		Assert.assertEquals(2, grouped.size());
		Assert.assertEquals(1, grouped.get(1).size());
		Assert.assertEquals(Integer.valueOf(1), grouped.get(1).get(0));
		Assert.assertEquals(Integer.valueOf(9), grouped.get(9).get(0));
	}
}
