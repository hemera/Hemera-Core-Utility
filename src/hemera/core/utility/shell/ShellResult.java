package hemera.core.utility.shell;

/**
 * <code>ShellResult</code> defines the immutable result
 * of executing a shell command.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.0
 */
public class ShellResult {
	/**
	 * The <code>int</code> result status code.
	 */
	public final int code;
	/**
	 * The <code>String</code> output.
	 */
	public final String output;
	
	/**
	 * Constructor of <code>ShellResult</code>.
	 * @param code The <code>int</code> result status
	 * code.
	 * @param output The <code>String</code> output.
	 */
	ShellResult(final int code, final String output) {
		this.code = code;
		this.output = output;
	}
}
