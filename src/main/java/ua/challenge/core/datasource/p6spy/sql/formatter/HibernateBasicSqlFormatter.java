package ua.challenge.core.datasource.p6spy.sql.formatter;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;

/**
 * Created by d.bakal on 22.04.2017.
 */
public class HibernateBasicSqlFormatter implements MessageFormattingStrategy {

    private final Formatter formatter = new BasicFormatterImpl();

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        String result = sql.isEmpty() ? prepared : sql;

        if (result.isEmpty()) {
            return "";
        }

        String template = "Hibernate: %s %s {elapsed: %sms}";
        String batch = "batch".equals(category) ? "batch.operation" : "";
        return String.format(template, batch, formatter.format(result), elapsed);
    }
}
