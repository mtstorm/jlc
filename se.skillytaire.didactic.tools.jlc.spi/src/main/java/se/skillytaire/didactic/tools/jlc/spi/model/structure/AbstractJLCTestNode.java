package se.skillytaire.didactic.tools.jlc.spi.model.structure;

import java.util.Optional;

import se.skillytaire.didactic.tools.jlc.api.Archetype;
import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.api.JLCConfigurationException;
import se.skillytaire.didactic.tools.jlc.api.TestObjectFactory;

public abstract class AbstractJLCTestNode<T> implements JLCTestNode<T> {

	private JLCConfiguration<T> configuration;
	private Archetype filter;
	protected final JLCConfiguration<T> getConfiguration() {
		if (configuration == null) {
			throw new IllegalStateException("Root configuration is void, check " + getClass().getName());
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
	 * @see se.skillytaire.didactic.tools.jlc.apiold.TestObjectFactory#createThis()
	 */
	protected final T createThis() {
		return getObjectFactory().createThis();
	}

	/**
	 * In order to run auto tests based on the bean under test, the object factory must be declared.
	 * @return
	 * @throws JLCConfigurationException when there is no object factory configured.
	 */
	protected final TestObjectFactory<T> getObjectFactory() throws JLCConfigurationException {
		if (!getConfiguration().hasObjectFactory()) {
			throw new JLCConfigurationException(
					"There is no primary object factory declared, using the extentions for JLC this must be declared @JLC( testFactory= ...class).");
		}
		return getConfiguration().getObjectFactory();
	}

	/**
	 * @return
	 * @see se.skillytaire.didactic.tools.jlc.apiold.TestObjectFactory#createThat()
	 */
	protected final T createThat() {
		return getObjectFactory().createThat();
	}

	/**
	 * @param numericValue
	 * @return
	 * @see se.skillytaire.didactic.tools.jlc.apiold.TestObjectFactory#create(long)
	 */
	protected final Optional<T> create(long numericValue) {
		return getObjectFactory().create(numericValue);
	}

	/**
	 * Get the type of the class that is currently under test.
	 * 
	 * @return
	 */
	protected final Class<?> type() {
		return getObjectFactory().type();
	}

	

	@Override
	public void applyFilter(Archetype archetype) {
		this.filter = archetype;
	}

	protected boolean isFiltered() {
		return this.filter != null;
	}

	protected Archetype getFilter() {
		return this.filter;
	}
}
