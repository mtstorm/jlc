package se.skillytaire.didactic.tools.jlc.spi.model.structure;

import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;
import se.skillytaire.didactic.tools.jlc.spi.model.Archetype;
import se.skillytaire.didactic.tools.jlc.spi.model.config.JLCConfiguration;

public abstract class AbstractJLCTestNode<T> implements JLCTestNode<T>{

	private JLCConfiguration<T> configuration;


	protected final JLCConfiguration<T> getConfiguration() {
		if(configuration == null) {
			throw new IllegalStateException("Root configuration is void, check "+ getClass().getName());
		}
		return configuration;
	}
	@Override
	public void init(JLCConfiguration<T> configuration) {
		if (configuration == null) {
			throw new AssertionError("jlc configuration is void");
		}
		this.configuration = configuration;
		
	}
	
	protected final Object getTestInstance() {
		return getConfiguration().getTestInstance();
	}
	/**
	 * @return
	 * @see se.skillytaire.didactic.tools.jlc.api.TestObjectFactory#createThis()
	 */
	protected final T createThis() {
		return getObjectFactory().createThis();
	}

	protected final TestObjectFactory<T> getObjectFactory() {
		return this.getConfiguration().getObjectFactory();
	}

	/**
	 * @return
	 * @see se.skillytaire.didactic.tools.jlc.api.TestObjectFactory#createThat()
	 */
	protected final T createThat() {
		return getObjectFactory().createThat();
	}

	/**
	 * @param numericValue
	 * @return
	 * @see se.skillytaire.didactic.tools.jlc.api.TestObjectFactory#create(long)
	 */
	protected final Optional<T> create(long numericValue) {
		return getObjectFactory().create(numericValue);
	}
	/**
	 * Get the type of the class that is currently under test.
	 * @return
	 */
	protected final Class<T> type() {
		return getObjectFactory().type();
	}

	private Archetype filter;

	@Override
	public void applyFilter(Archetype archetype) {
		this.filter = archetype;
		//System.out.println("apply filter "+ archetype);
	}

	protected boolean isFiltered() {
		return this.filter != null;
	}
	
	protected Archetype getFilter() {
		return this.filter;
	}
}
