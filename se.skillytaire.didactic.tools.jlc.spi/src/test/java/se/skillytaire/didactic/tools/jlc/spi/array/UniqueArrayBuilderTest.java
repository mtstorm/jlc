package se.skillytaire.didactic.tools.jlc.spi.array;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class UniqueArrayBuilderTest {

	@Test
	void testAddArrSingle() {
		Integer[] arr1 = {0,1};
		Integer[] arr2 = {2,3};
		Integer[] expected = {0,1,2,3};
		UniqueArrayBuilder<Integer> beanUnderTest = new UniqueArrayBuilder<Integer>(arr1);
		beanUnderTest.append(arr2);
		Integer[] actual = beanUnderTest.toArray();
		assertArrayEquals(expected, actual);
	}
	@Test
	void testAddArrDuplicates() {
		Integer[] arr1 = {0,1};
		Integer[] arr2 = {1,2};
		Integer[] expected = {0,1,2};
		UniqueArrayBuilder<Integer> beanUnderTest = new UniqueArrayBuilder<Integer>(arr1);
		beanUnderTest.append(arr2);
		Integer[] actual = beanUnderTest.toArray();
		assertArrayEquals(expected, actual);
	}
	@Test
	void testAddSingle() {
		Integer[] arr1 = {0,1};
		Integer[] expected = {0,1,2};
		UniqueArrayBuilder<Integer> beanUnderTest = new UniqueArrayBuilder<Integer>(arr1);
		beanUnderTest.append(2);
		Integer[] actual = beanUnderTest.toArray();
		assertArrayEquals(expected, actual);
	}
	@Test
	void testAddDuplicates() {
		Integer[] arr1 = {0,1};

		Integer[] expected = {0,1};
		UniqueArrayBuilder<Integer> beanUnderTest = new UniqueArrayBuilder<Integer>(arr1);
		beanUnderTest.append(1);
		Integer[] actual = beanUnderTest.toArray();
		assertArrayEquals(expected, actual);
	}
}
