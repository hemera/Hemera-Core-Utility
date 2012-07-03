package hemera.core.utility.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <code>Shell</code> defines the singleton utility
 * representation of the hosting operating system's
 * shell command.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.0
 */
public enum Shell {
	/**
	 * The singleton instance.
	 */
	instance;

	/**
	 * Execute the given command.
	 * @param command The <code>String</code> command.
	 * @return The execution <code>ShellResult</code>.
	 * @throws IOException If an I/O error occurred.
	 * @throws InterruptedException If waiting for the
	 * execution to complete was interrupted.
	 */
	public ShellResult execute(final String command) throws IOException, InterruptedException {
		final Process process = Runtime.getRuntime().exec(command);
		final BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		final StringBuilder output = new StringBuilder();
		String line = input.readLine();
		while (line != null) {
			output.append(line).append("\n");
		}
		input.close();
		final int code = process.waitFor();
		return new ShellResult(code, output.toString());
	}
	
	/**
	 * Execute the given command with root privilege.
	 * @param command The <code>String</code> command.
	 * @return The execution <code>ShellResult</code>.
	 * @throws IOException If an I/O error occurred.
	 * @throws InterruptedException If waiting for the
	 * execution to complete was interrupted.
	 */
	public ShellResult executeAsRoot(final String command) throws IOException, InterruptedException {
		return this.execute("sudo " + command);
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
		final ShellResult result = this.execute("chmod +x " + target);
		if (result.code != 0) throw new IOException("Making hemera script executable failed.\n" + result.output);
	}
}
