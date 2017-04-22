package ua.challenge.core.datasource.latency;

import lombok.SneakyThrows;
import lombok.experimental.Delegate;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by d.bakal on 22.04.2017.
 */
public class LatencyResultSet implements ResultSet {
    @Delegate(excludes = ResultSetAware.class)
    private final ResultSet realResultSet;

    public LatencyResultSet(ResultSet realResultSet) {
        this.realResultSet = realResultSet;
    }

    @Override
    @SneakyThrows
    public boolean next() throws SQLException {
        Thread.sleep(2);
        return realResultSet.next();
    }

    @Override
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        return realResultSet.getObject(columnIndex, type);
    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        return realResultSet.getObject(columnLabel, type);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return realResultSet.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return realResultSet.isWrapperFor(iface);
    }

    private interface ResultSetAware {
        boolean next() throws SQLException;

        <T> T getObject(int columnIndex, Class<T> type);

        <T> T getObject(String columnLabel, Class<T> type);

        <T> T unwrap(Class<T> iface) throws SQLException;

        boolean isWrapperFor(Class<?> iface);
    }
}
