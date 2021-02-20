package se.skillytaire.didactic.tools.jlc.signature.spi.model.config;

import java.lang.reflect.Executable;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.Optional;
import java.util.logging.Logger;

import se.skillytaire.didactic.tools.jlc.api.JLCConfiguration;
import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.naming.SignatureDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.config.AbstractTestConfiguration;
/**
 * An abstraction to be reused 
 *
 * @param <N>
 * @param <T>
 * @param <S>
 * @param <E>
 */
public abstract class AbstractTestSignatureConfiguration
<N extends AbstractTestSignatureConfiguration<N,T, S,E,D>, 
T, 
S extends Signature, 
E extends Executable,
D extends JLCFeatureConfiguration>
		extends AbstractTestConfiguration<D,N,T> implements TestSignatureConfiguration<N,T, S,E,D> {

	private E executor;

	private final S message;

	private int maximalParameterCount;

	private Class<? extends Exception> nullCheck;

	private boolean simpleName;
	
	private boolean dbcEnabled = true;

	protected AbstractTestSignatureConfiguration(JLCConfiguration<T> parent,D defaults, S message) {
		super(parent,defaults);
		if (message == null) {
			throw new IllegalArgumentException("message is void");
		}
		this.message = message;
	}

	public AbstractTestSignatureConfiguration(JLCConfiguration<T> parent,D defaults, S message,
			Class<? extends Exception> nullCheck) {
		this(parent,defaults, message);
		this.nullCheck = nullCheck;
	}

	public final int getMaximalParameterCount() {
		return maximalParameterCount;
	}

	public final void setMaximalParameterCount(int maximalParameterCount) {
		this.maximalParameterCount = maximalParameterCount;
	}
	public final boolean isSimpleName() {
		return simpleName;
	}

	public final void setSimpleName(boolean simpleName) {
		this.simpleName = simpleName;
	}
	public final void setNullCheck(Class<? extends Exception> nullCheck) {
		this.nullCheck = nullCheck;
	}

	public final Class<? extends Exception> getNullCheck() {
		return nullCheck;
	}

	@Override
	public final S getSignature() {
		return this.message;
	}

	public boolean isDbcEnabled() {
		return dbcEnabled;
	}

	public void setDbcEnabled(boolean dbcEnabled) {
		this.dbcEnabled = dbcEnabled;
	}



	@Override
	public String toString() {
		return String.format(
				"%s executor=%s, message=%s, maximalParameterCount=%s, nullCheck=%s, dbcEnabled=%s, ",
				super.toString(), executor, message, maximalParameterCount, nullCheck, isDbcEnabled());
	}

	public final E getExecutor() {
		if (!hasExecutor()) {
			throw new IllegalStateException("There is no constructor for the message " + getSignature());
		}
		return executor;
	}

	public final boolean hasExecutor() {
		return executor != null;
	}

	/**
	 * @param method the method to set
	 */
	public final void setExecutor(E executor) {
		this.executor = executor;
	}

	public final Optional<Class<?>> declaringClass() {
		Class<?> declaringClass = null;
		if (hasExecutor()) {
			declaringClass = executor.getDeclaringClass();
		}
		return Optional.ofNullable(declaringClass);
	}

	@Override
	public final String getName() {
		String displayName;
		if(hasDisplayNameValue()) {
			displayName = getDisplayNameValue();
		}else {
			SignatureDisplayName result = new SignatureDisplayName(getSignature());
			result.setShortSignature(isSimpleName());
			displayName = result.value();
		}

		return displayName;
	}
	private Logger log = Logger.getLogger(AbstractTestSignatureConfiguration.class.getName());
	public final Optional<URI> toUri() {
		URI uri = null;
		Optional<Class<?>> declaringClass = declaringClass();
		if (declaringClass.isPresent()) {
			Class<?> type = declaringClass.get();
			//FIXME method URI builder creation
			uri = this.getSignature().toUri(type);
			//System.out.println("Signature URI -> "+ uri);
		} else {
			log.warning("There is no declaring class for the signature "+ this.getSignature().toString());
		}
		//System.out.println(uri);
		return Optional.ofNullable(uri);
	}

	/**
	 * Checks if the underlaying method is public
	 * 
	 * @return
	 */
	@Override
	public final boolean isVisible() {
		return this.hasExecutor() && !Modifier.isPrivate(this.getExecutor().getModifiers());
	}


	@Override
	public final boolean isInvisible() {
		return this.hasExecutor() && Modifier.isPrivate(this.getExecutor().getModifiers());
	}

	/**
	 * check it the configuration is based on a declared annotation.
	 */

	@Override
	public void merge(N n) {
		super.merge(n);
		if(n.getMaximalParameterCount() > this.getMaximalParameterCount()) {
			setMaximalParameterCount(n.getMaximalParameterCount());
		}	
		this.setNullCheck(n.getNullCheck());
		//this.setDbcEnabled(dbcEnabled);
	}
	public boolean isOverloaded(N that) {
		return getSignature().isOverloaded(that.getSignature());
	}
}
