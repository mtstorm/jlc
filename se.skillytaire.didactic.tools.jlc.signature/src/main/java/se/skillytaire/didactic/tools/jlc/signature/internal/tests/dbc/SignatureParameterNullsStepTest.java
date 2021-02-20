package se.skillytaire.didactic.tools.jlc.signature.internal.tests.dbc;

import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;

import se.skillytaire.didactic.tools.jlc.signature.internal.tests.AbstractSignatureTestNode;
import se.skillytaire.didactic.tools.jlc.signature.spi.Signature;
import se.skillytaire.didactic.tools.jlc.signature.spi.model.config.TestSignatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.e.JLCFeatureConfiguration;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.BasicDisplayName;
import se.skillytaire.didactic.tools.jlc.spi.model.naming.DisplayName;

public class SignatureParameterNullsStepTest<N extends TestSignatureConfiguration<N,T,S,E,D>,T, S extends Signature,E extends Executable,D extends JLCFeatureConfiguration> extends AbstractSignatureTestNode<N,T,S,E,D> {
	private final Object[] parameterValues;

	public SignatureParameterNullsStepTest(N testConfiguration, Object[] parameterValues) {
		super(testConfiguration);
		this.parameterValues = parameterValues;
		String assertMessage;
		if (getTestConfiguration().isVisible()) {
			assertMessage = "Should throw an IllegalArgumentException";
		} else if (getTestConfiguration().isInvisible()) {
			assertMessage = "Should throw an AssertionError";
		} else {
			assertMessage = "Non resolved problem";
		}
		setAssertMessage(assertMessage);
	}

	@Override
	public void execute() throws Throwable {
		T newInstance = this.createThis();
		boolean succes = false;
		try {
			getTestConfiguration().invoke( parameterValues);
			Assertions.fail(getAssertMessage());
		} catch (InvocationTargetException e) {
			if (getTestConfiguration().isVisible()) {
				Class<?> ex = getTestConfiguration().getNullCheck();
			//	System.out.println("The exception "+ex);
				if (ex == e.getTargetException().getClass()) {
					succes = true;
				} else {
					String msg = String.format(
							"Expected an %s for a null parameter of a non private method, but was %s", ex.getName(),
							e.getTargetException());

					Assertions.fail(msg, e.getTargetException());
				}
			} else if (getTestConfiguration().isInvisible()) {
				if (AssertionError.class == e.getTargetException().getClass()) {
					succes = true;
				} else {
					String msg = String.format(
							"Expected an AssertionError for a null parameter of a private method, but was %s",
							e.getTargetException());
					Assertions.fail(msg, e.getTargetException());

				}
			} else {
				Assertions.fail(getAssertMessage(), e.getTargetException());
			}

		} catch (Exception e) {
			Assertions.fail("General invocation problem...", e);
			
		}
		
	}



	@Override
	public DisplayName getDisplayName() {
		String[] values = new String[parameterValues.length];
		Arrays.stream(parameterValues).map(Objects::toString).collect(Collectors.toList()).toArray(values);
		String description = getTestConfiguration().getTestDescription(values);
		return new BasicDisplayName(description);
	}

}
