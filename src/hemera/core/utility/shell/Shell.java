package hemera.core.utility.shell;

import hemera.core.utility.FileUtils;

import java.io.IOException;

/**
 * <code>Shell</code> defines the singleton utility
 * representation of the hosting operating system's
 * shell command.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.6
 */
public enum Shell {
	/**
	 * The singleton instance.
	 */
	instance;
	
	/**
	 * Execute the given command array.
	 * @param command The <code>String</code> array of
	 * command.
	 * @return The execution <code>ShellResult</code>.
	 * @throws IOException If an I/O error occurred.
	 * @throws InterruptedException If waiting for the
	 * execution to complete was interrupted.
	 */
	public ShellResult execute(final String[] command, final boolean asRoot) throws IOException, InterruptedException {
		Process process = null;
		if (asRoot) {
			final String[] array = new String[command.length+1];
			array[0] = "sudo";
			System.arraycopy(command, 0, array, 1, command.length);
			process = Runtime.getRuntime().exec(array);
		} else {
			process = Runtime.getRuntime().exec(command);
		}
		return this.readResult(process);
	}
	
	/**
	 * Read the shell result from given process.
	 * @param process The <code>Process</code> to read.
	 * @return The <code>ShellResult</code> instance.
	 * @throws IOException If an I/O error occurred.
	 * @throws InterruptedException If waiting for the
	 * execution to complete was interrupted.
	 */
	private ShellResult readResult(final Process process) throws IOException, InterruptedException {
		final String output = FileUtils.instance.readAsString(process.getInputStream());
		final String error = FileUtils.instance.readAsString(process.getErrorStream());
		final StringBuilder builder = new StringBuilder();
		builder.append(output).append("\n").append(error);
		final int code = process.waitFor();
		return new ShellResult(code, builder.toString());
	}
	
	/**
	 * Make the given target executable.
	 * @param target The <code>String</code> target
	 * path.
	 * @throws IOException If changing mode failed.
	 * @throws InterruptedException If the command
	 * is interrupted.
	 */
	public void makeExecutable(final String target) throws IOException, InterruptedException {
		final ShellResult result = this.execute(new String[] {"chmod", "+x", target}, true);
		if (result.code != 0) throw new IOException("Making hemera script executable failed.\n" + result.output);
	}
}
