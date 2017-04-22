package ua.challenge.core.datasource.latency;

import lombok.experimental.Delegate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by d.bakal on 22.04.2017.
 */
public class LatencyDatasource implements DataSource {
    @Delegate(excludes = LatencyDatasource.ConnectionAware.class)
    private final DataSource realDataSource;
    private final LatencySimulator latencySimulator;

    public LatencyDatasource(DataSource realDataSource, LatencySimulator latencySimulator) {
        this.realDataSource = realDataSource;
        this.latencySimulator = latencySimulator;
    }

    @Override
    public Connection getConnection() throws SQLException {
        final Connection connection = realDataSource.getConnection();
        return new LatencyConnection(connection, latencySimulator);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        final Connection connection = realDataSource.getConnection(username, password);
        return new LatencyConnection(connection, latencySimulator);
    }

    private interface ConnectionAware {
        Connection getConnection() throws SQLException;

        Connection getConnection(String username, String password) throws SQLException;
    }
}
