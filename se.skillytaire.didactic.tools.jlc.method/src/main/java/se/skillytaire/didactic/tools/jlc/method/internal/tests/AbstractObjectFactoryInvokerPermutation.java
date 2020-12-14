package se.skillytaire.didactic.tools.jlc.method.internal.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import se.skillytaire.didactic.tools.jlc.api.ComparableTestObjectFactory;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

public abstract class AbstractObjectFactoryInvokerPermutation<T,R> 
implements 

TestPartObjectFactory<T, R>


{
	private static final int RESET = -1;

	private int counter = RESET;

	private final List<Supplier<T>> suppliers;
	private final String[] roleNames = {"this","that","lessThen","greaterThen"};

	
	
	private final TestObjectFactory<T> testFactory;
	
//	private final int power;

	private TestObjectFactory<T> current;

	public AbstractObjectFactoryInvokerPermutation(TestObjectFactory<T> testFactory) {
		if(testFactory == null) {
			throw new IllegalArgumentException("test factory is void");
		}
		this.testFactory = testFactory;
		suppliers = new ArrayList<Supplier<T>>(4);
		suppliers.add( () -> testFactory.createThis());
		suppliers.add( () -> testFactory.createThat());
		
		if(testFactory instanceof ComparableTestObjectFactory) {
			
			ComparableTestObjectFactory<T> ctf = (ComparableTestObjectFactory<T>) testFactory;
			suppliers.add( () -> ctf.createLessThen());
			suppliers.add( () -> ctf.createGreaterThen());
		}
		size = calculateSize();
	}

	private final int size;
	
	protected abstract int calculateSize();
	
	
	protected abstract Supplier<T> getThisSupplier();
	protected abstract Supplier<T> getThatSupplier();
	
	
	@Override
	public final boolean hasNext() {
		return counter < size;
	}
	/**
	 * The 2 power for the permutation
	 * @return
	 */
	protected final int getPower() {
		return this.suppliers.size();
	}

	
	protected final int getCounter() {
		return counter;
	}
	
	@Override
	public final T createThis() {
		assertIterationHasBeenStarted();
		return current.createThis();
	}

	@Override
	public final T createThat() {
		assertIterationHasBeenStarted();
		return current.createThat();
	}
	
	protected void assertIterationHasBeenStarted() {
		if(this.current == null) {
			throw new IllegalStateException("The iteration has not been started");
		}
	}
	/**
	 * Returns null when iteration has not been started
	 */
	public final Optional<TestObjectFactory<T>> get(){
		return  Optional.ofNullable(current);
	}
	@Override
	public final TestPartObjectFactory<T, R> next() {
		current = new TestObjectFactory<T>() {

			@Override
			public T createThis() {
				return getThisSupplier().get();
			}

			@Override
			public T createThat() {
				return getThatSupplier().get();
			}

			@Override
			public Class<T> type() {
				return AbstractObjectFactoryInvokerPermutation.this.testFactory.type();
			}

			@Override
			public boolean isTypeFor(Class<?> type) {
				return AbstractObjectFactoryInvokerPermutation.this.testFactory.isTypeFor(type);
			}
		};

		this.counter++;
		return this;
	}
	
	public Stream<TestPartObjectFactory<T, R>> stream(){
		reset();
		Stream<TestPartObjectFactory<T, R>> stream = StreamSupport.stream(
            Spliterators.spliterator(this, size, Spliterator.ORDERED | Spliterator.SIZED)
            , false);
		return stream;
	}
	
	private void reset() {
		this.counter = RESET; 
	}
	@Override
	public final Class<T> type() {
		return this.testFactory.type();
	}

	@Override
	public final boolean isTypeFor(Class<?> type) {
		return this.testFactory.isTypeFor(type);
	}
	/**
	 * Get the role name for the given index.
	 * @param index between 0 en {@code #getPower()}
	 * @return the role name for the given index.
	 */
	protected final String getRoleName(int index) {
		if(index >= getPower()) {
			String msg = String.format("The index %d must %d >= %d", index, index, getPower());
			throw new IllegalArgumentException(msg);
		}
		return roleNames[index];
	}
	/**
	 * Get the supplier for the given index.
	 * @param index between 0 en {@code #getPower()}
	 * @return the supplier name for the given index.
	 */
	protected final Supplier<T> getSupplier(int index) {
		if(index >= getPower()) {
			String msg = String.format("The index %d must %d >= %d", index, index, getPower());
			throw new IllegalArgumentException(msg);
		}
		return this.suppliers.get(index);
	}
}
