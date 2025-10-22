package es.golemdr.rrcc.mantenimiento.ext.p6spy;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class SimpleSqlFormatter implements MessageFormattingStrategy {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneId.systemDefault());

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category,
                                String prepared, String sql, String url) {
        if (sql == null || sql.trim().isEmpty()) {
            return "";
        }

        // Filtrar solo DML (INSERT, UPDATE, DELETE)
        String upper = sql.trim().toUpperCase();
        if (!(upper.startsWith("INSERT") || upper.startsWith("UPDATE") || upper.startsWith("DELETE"))) {
            return ""; // Ignorar SELECT, COMMIT, etc.
        }

        // Timestamp legible
        String timestamp = FORMATTER.format(Instant.now());

        return timestamp + "||" + sql;
    }
}


