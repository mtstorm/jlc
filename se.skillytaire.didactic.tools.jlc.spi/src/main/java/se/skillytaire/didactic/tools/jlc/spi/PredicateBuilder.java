package se.skillytaire.didactic.tools.jlc.spi;

import java.util.function.Predicate;


public class PredicateBuilder<T> {
	private Predicate<T> result;
	private PredicateBuilder(Predicate<T> start) {
		this.result = start;
	}
	public static <T> PredicateBuilder<T> of(Predicate<T> start) {
		return new PredicateBuilder<T>(start);
	}
	public static <T> PredicateBuilder<T> nof(Predicate<T> start) {
		return new PredicateBuilder<T>(start.negate());
	}
	public PredicateBuilder<T> and(Predicate<? super T> other) {
		result = result.and(other);
		return this;
	}


	public PredicateBuilder<T> or(Predicate<? super T> other) {
		result = result.or(other);
		return this;
	}

	public PredicateBuilder<T> nand(Predicate<? super T> other) {
		result = result.and(other.negate());
		return this;
	}


	public PredicateBuilder<T> nor(Predicate<? super T> other) {
		result = result.or(other.negate());
		return this;
	}
	
	public Predicate<T> build(){
		return this.result;
	}
	
}
