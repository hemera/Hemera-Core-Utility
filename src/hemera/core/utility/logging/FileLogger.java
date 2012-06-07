package hemera.core.utility.logging;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import hemera.core.utility.enumn.ELoggingConfig;

/**
 * <code>FileLogger</code> defines the implementation
 * of a configured <code>Logger</code> unit that is
 * setup to export log records to a set of external
 * files in a rotational fashion.
 * <p>
 * <code>FileLogger</code> saves log record files in
 * the format of <code>classname.log.x</code> where
 * class name is the name of the class given to the
 * <code>get</code> method and x indicates the index
 * of the file in the set.
 * <p>
 * <code>FileLogger</code> sets up the instances of
 * <code>Logger</code> based on the configuration in
 * <code>ELoggingConfig</code> and provides three-
 * level based logging methods. All provided logging
 * methods use <code>ELoggingConfig</code> values at
 * runtime for logging invocations.
 *
 * @author Yi Wang (Neakor)
 * @version Creation date: 08-08-2010 18:09 EST
 * @version Modified date: 09-06-2010 22:12 EST
 */
public final class FileLogger {
	/**
	 * The <code>ReadWriteLock</code> used to guard
	 * the logger instance cache.
	 */
	private static final ReadWriteLock lock = new ReentrantReadWriteLock();
	/**
	 * The <code>Map</code> of the <code>String</code>
	 * class-name key to the <code>FileLogger</code>
	 * instance that is created using the class-name.
	 * <p>
	 * This structure is guarded by the read-write lock.
	 */
	private static final Map<String, FileLogger> cache = new HashMap<String, FileLogger>();
	/**
	 * The <code>AtomicReference</code> of the standard
	 * out <code>FileHandler</code> that is used on all
	 * the logger instances as a console output stream.
	 * <p>
	 * Atomic check-and-set operation is needed to
	 * ensure that only a single object instance is
	 * set and stored.
	 */
	private static final AtomicReference<FileHandler> consoleHandler = new AtomicReference<FileHandler>(null);
	/**
	 * The <code>Logger</code> instance.
	 */
	private final Logger logger;

	/**
	 * Constructor of <code>FileLogger</code>.
	 * @param logger The configured <code>Logger</code>
	 * instance.
	 */
	private FileLogger(final Logger logger) {
		this.logger = logger;
	}

	/**
	 * Log an info level message, if logging is enabled
	 * at <code>ELoggingConfig.Enabled</code>.
	 * @param message The <code>String</code> message
	 * to be logged.
	 */
	public void info(final String message) {
		final Boolean enabled = (Boolean)ELoggingConfig.Enabled.getValue();
		if (!enabled) return;
		this.logger.info(message);
	}

	/**
	 * Log a warning level message, if logging is enabled
	 * at <code>ELoggingConfig.Enabled</code>.
	 * @param message The <code>String</code> message
	 * to be logged.
	 */
	public void warning(final String message) {
		final Boolean enabled = (Boolean)ELoggingConfig.Enabled.getValue();
		if (!enabled) return;
		this.logger.warning(message);
	}

	/**
	 * Log a severe level message, if logging is enabled
	 * at <code>ELoggingConfig.Enabled</code>.
	 * @param message The <code>String</code> message
	 * to be logged.
	 */
	public void severe(final String message) {
		final Boolean enabled = (Boolean)ELoggingConfig.Enabled.getValue();
		if (!enabled) return;
		this.logger.severe(message);
	}

	/**
	 * Log the given exception, if logging is enabled
	 * at <code>ELoggingConfig.Enabled</code>.
	 * @param exception The <code>Exception</code> to
	 * be logged.
	 */
	public void exception(final Exception exception) {
		final Boolean enabled = (Boolean)ELoggingConfig.Enabled.getValue();
		if (!enabled) return;
		final StringBuilder builder = new StringBuilder();
		builder.append(exception.toString()).append("\n");
		for(StackTraceElement e : exception.getStackTrace()) {
			builder.append("		at ").append(e.toString()).append("\n");
		}
		this.logger.severe(builder.toString());
	}

	/**
	 * Retrieve a logger instance for the given class.
	 * <p>
	 * If a logger instance has already been created,
	 * the instance is directly returned allowing the
	 * reuse of the same instance instead of creating
	 * a new one.
	 * <p>
	 * This method provides necessary thread-safety
	 * guarantees to allow concurrent retrievals with
	 * high concurrency capabilities. It ensures that
	 * a single logger instance is created for the
	 * same class.
	 * @param c The <code>Class</code> that uses the
	 * returned <code>FileLogger</code> instance and
	 * as the key to look up existing loggers.
	 * @return The <code>FileLogger</code> instance
	 * with given class-name as key.
	 */
	public static FileLogger getLogger(final Class<?> c) {
		final String classname = c.getName();
		// Read lock.
		FileLogger.lock.readLock().lock();
		try {
			FileLogger instance = FileLogger.cache.get(classname);
			// Create a new instance.
			if (instance == null) {
				// Lock write.
				FileLogger.lock.readLock().unlock();
				FileLogger.lock.writeLock().lock();
				try {
					// Second check.
					instance = FileLogger.cache.get(classname);
					if (instance == null) {
						instance = FileLogger.newFileLogger(classname);
						FileLogger.cache.put(classname, instance);
					}
				} finally {
					FileLogger.lock.readLock().lock();
					FileLogger.lock.writeLock().unlock();
				}
			}
			return instance;
		} finally {
			FileLogger.lock.readLock().unlock();
		}
	}
	
	/**
	 * Create a new instance of file logger with given
	 * class name as the file pattern.
	 * @param classname The <code>String</code> class-
	 * name of the class that uses the logger.
	 * @return The <code>FileLogger</code> instance.
	 */
	private static FileLogger newFileLogger(final String classname) {
		try {
			// Create file handler using class name.
			final FileHandler handler = FileLogger.newFileHandler(classname);
			// Create logger with file handler.
			final Logger logger = Logger.getLogger(classname);
			logger.addHandler(handler);
			// Add in standard file handler.
			FileHandler console = FileLogger.consoleHandler.get();
			if (console == null) {
				console = FileLogger.newFileHandler("console");
				final boolean succeeded = FileLogger.consoleHandler.compareAndSet(null, console);
				// Use singleton.
				if (!succeeded) console = FileLogger.consoleHandler.get();
			}
			logger.addHandler(console);
			// Return.
			return new FileLogger(logger);
		} catch (SecurityException e) {
			// Should not occur.
			e.printStackTrace();
		} catch (IOException e) {
			// Should not occur.
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Create a new file handler using the given name
	 * as file pattern, based on configuration values
	 * in <code>ELoggingConfig</code>.
	 * @param name The <code>String</code> name pattern
	 * to be used for log files.
	 * @return The <code>FileHandler</code> instance.
	 * @throws SecurityException If construction failed.
	 * @throws IOException If construction failed.
	 */
	private static FileHandler newFileHandler(final String name) throws SecurityException, IOException {
		// Create a file handler based on logging configuration.
		final String directory = (String)ELoggingConfig.Directory.getValue();
		final StringBuilder pattern = new StringBuilder();
		pattern.append(directory);
		if (!directory.endsWith("/")) pattern.append("/");
		pattern.append(name).append(".log");
		final Integer filesize = (Integer)ELoggingConfig.FileSize.getValue();
		final Integer filecount = (Integer)ELoggingConfig.FileCount.getValue();
		final FileHandler handler = new FileHandler(pattern.toString(), filesize, filecount, false);
		final LoggingFormatter formatter = new LoggingFormatter();
		handler.setFormatter(formatter);
		return handler;
	}
}
