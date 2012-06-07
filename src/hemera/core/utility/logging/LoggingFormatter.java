package hemera.core.utility.logging;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * <code>LoggingFormatter</code> defines an utility
 * unit implementation that formats the log record.
 *
 * @author Yi Wang (Neakor)
 * @version Creation date: 08-08-2010 15:29 EST
 * @version Modified date: 08-08-2010 15:34 EST
 */
public class LoggingFormatter extends Formatter {

	@Override
	@SuppressWarnings("deprecation")
	public String format(LogRecord record) {
		final Date date = new Date(record.getMillis());
		final int year = date.getYear() + 1900;
		final int month = date.getMonth() + 1;
		final int day = date.getDate();
		final int hour = date.getHours();
		final int min = date.getMinutes();
		final int sec = date.getSeconds();
		final String classname = record.getSourceClassName();
		final String methodname = record.getSourceMethodName();
		final String level = record.getLevel().toString();
		final String message = record.getMessage();
		final StringBuilder builder = new StringBuilder();
		builder.append(month).append("-").append(day).append("-").append(year).append(" ");
		builder.append(hour).append(":").append(min).append(":").append(sec).append(" ");
		builder.append(classname).append(" ").append(methodname).append("\n");
		builder.append(level).append(": ").append(message).append("\n");
		return builder.toString();
	}
}
