package CustomLogging;

import java.io.*;
import java.util.logging.*;

/**
 * Print a brief summary of the LogRecord in a human readable
 * format.  The summary will typically be 1 or 2 lines.
 *
 * @version %I%, %G%
 * @since 1.4
 */

public class SuperSimpleFormatter extends Formatter {

//    Date dat = new Date();
//    private final static String format = "{0,date} {0,time}";
//    private MessageFormat formatter;
//
//    private Object args[] = new Object[1];

    // Line separator string.  This is the value of the line.separator
    // property at the moment that the SimpleFormatter was created.
    private String lineSeparator = "\n";

    /**
     * Format the given LogRecord.
     * @param record the log record to be formatted.
     * @return a formatted log record
     */
    public synchronized String format(LogRecord record) {
	    StringBuffer sb = new StringBuffer();
	    // Minimize memory allocations here.
//	    dat.setTime(record.getMillis());
//	    args[0] = dat;
//	    StringBuffer text = new StringBuffer();
//	    if (formatter == null) {
//	        formatter = new MessageFormat(format);
//	    }
//	    formatter.format(args, text, null);
//	    sb.append(text);
//	    sb.append(" ");
	    if (record.getSourceClassName() != null) {  
	        sb.append(record.getSourceClassName());
	    } else {
	        sb.append(record.getLoggerName());
	    }
	    if (record.getSourceMethodName() != null) { 
	        sb.append(" ");
	        sb.append(record.getSourceMethodName());
	    }
	    sb.append(lineSeparator);
	    String message = formatMessage(record);
	    if (record.getLevel().getLocalizedName().equals("STDERR")){
	    	sb.append("ERROR: ");
	    }
	    sb.append(message);
//	    sb.append(lineSeparator);
	    if (record.getThrown() != null) {
	        try {
	            StringWriter sw = new StringWriter();
	            PrintWriter pw = new PrintWriter(sw);
	            record.getThrown().printStackTrace(pw);
	            pw.close();
	        sb.append(sw.toString());
	        } catch (Exception ex) {
	        }
	    }
	    return sb.toString();
    }
}
