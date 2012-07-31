package hemera.core.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * <code>Compiler</code> defines the implementation
 * that provides the functionality to compile a set
 * of Java files in a directory with a specified
 * library class path into a single Jar file.
 *
 * @author Yi Wang (Neakor)
 * @version 1.0.0
 */
public class Compiler {

	/**
	 * Compile the source files in the specified source
	 * directory including all of its sub-directories
	 * with all the given dependency files as compiling
	 * class-path into given build directory. The build
	 * directory will preserve the package structure of
	 * the source directory.
	 * @param srcDir The <code>String</code> root source
	 * files directory.
	 * @param buildDir The <code>String</code> directory
	 * to put all compiled class files.
	 * @param dependencies The <code>List</code> of all
	 * dependencies <code>File</code>.
	 * @throws Exception If any compilation error occurred.
	 */
	public void compile(final String srcDir, final String buildDir, final List<File> dependencies) throws Exception {
		// Retrieve all Java files.
		final List<File> sourcefiles = FileUtils.instance.getFiles(srcDir, ".java");
		if (sourcefiles == null || sourcefiles.isEmpty()) return;
		// Create the compiler.
		final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		final DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		final StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
		final Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(sourcefiles);
		// Create task with compiler options.
		// Class files are left at the where the corresponding source files are.
		final List<String> options = this.getCompileOptions(dependencies);
		final CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits);
		final boolean succeeded = task.call();
		fileManager.close();
		if (!succeeded) {
			final StringBuilder builder = new StringBuilder();
			builder.append("Compiling source at directory: ");
			builder.append(srcDir).append(" failed.\n");
			final List<Diagnostic<? extends JavaFileObject>> errors = diagnostics.getDiagnostics();
			for (final Diagnostic<? extends JavaFileObject> error : errors) {
				builder.append(error.toString()).append("\n");
			}
			throw new RuntimeException(builder.toString());
		}
		// Copy the source directory which will include all the class and source
		// files in the proper package structure, but only include class files.
		FileUtils.instance.copyFolder(srcDir, buildDir, ".class");
		// Remove all class files from the source directory.
		final List<File> classfiles = FileUtils.instance.getFiles(srcDir, ".class");
		final int size = classfiles.size();
		for (int i = 0; i < size; i++) classfiles.get(i).delete();
	}
	
	/**
	 * Create the compile class-path and destination
	 * directory options.
	 * @param dependencies The <code>List</code> of all
	 * dependencies <code>File</code>.
	 * @return The <code>List</code> of compile option
	 * <code>String</code> values.
	 */
	private List<String> getCompileOptions(final List<File> dependencies) {
		if (dependencies == null || dependencies.isEmpty()) return null;
		final List<String> options = new ArrayList<String>();
		// Append all Jar files as class-path option.
		options.add("-classpath");
		final StringBuilder builder = new StringBuilder();
		final int size = dependencies.size();
		final int last = size - 1;
		for (int i = 0; i < size; i++) {
			final File libFile = dependencies.get(i);
			builder.append(libFile.getAbsolutePath());
			if (i != last) builder.append(":");
		}
		options.add(builder.toString());
		return options;
	}
}
