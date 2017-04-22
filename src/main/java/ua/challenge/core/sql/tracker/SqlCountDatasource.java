package ua.challenge.core.sql.tracker;

import lombok.experimental.Delegate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by d.bakal on 22.04.2017.
 */
public class SqlCountDatasource implements DataSource {
    @Delegate(excludes = ConnectionAware.class)
    private DataSource realDataSource;

    public SqlCountDatasource(DataSource realDataSource) {
        this.realDataSource = realDataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        final Connection connection = realDataSource.getConnection();
        return new SqlCountConnection(connection);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        final Connection connection = realDataSource.getConnection(username, password);
        return new SqlCountConnection(connection);
    }

    private interface ConnectionAware {
        Connection getConnection() throws SQLException;

        Connection getConnection(String username, String password) throws SQLException;
    }
}
