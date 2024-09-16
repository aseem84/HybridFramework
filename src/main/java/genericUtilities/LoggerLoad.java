package genericUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerLoad {
	private LoggerLoad() {
		// Private constructor to prevent instantiation from outside the class.
		throw new AssertionError("Cannot instantiate this utility class.");
	}

	private static Logger logger = LogManager.getLogger();

	public static void trace(String message) {
		logger.trace("{}: {}", getCallerInfo(), message);
	}

	public static void debug(String message) {
		logger.debug("{}: {}", getCallerInfo(), message);
	}

	public static void info(String message) {
		logger.info("{}: {}", getCallerInfo(), message);

	}

	public static void warn(String message) {
		logger.warn("{}: {}", getCallerInfo(), message);
	}

	public static void error(String message) {
		logger.error("{}: {}", getCallerInfo(), message);
	}

	public static void fatal(String message) {
		logger.fatal("{}: {}", getCallerInfo(), message);
	}

	private static String getCallerInfo() {
	    StackTraceElement caller = Thread.currentThread().getStackTrace()[3];
		return "[" + caller.getClassName() + " => " + caller.getMethodName() + "]";
	}
	//done the modifications
}
