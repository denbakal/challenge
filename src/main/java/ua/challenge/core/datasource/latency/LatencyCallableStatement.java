package ua.challenge.core.datasource.latency;

import lombok.experimental.Delegate;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;

/**
 * Created by d.bakal on 22.04.2017.
 */
public class LatencyCallableStatement extends LatencyPreparedStatement implements CallableStatement {
    @Delegate(excludes = ExecutionAware.class)
    private final CallableStatement realCallableStatement;

    public LatencyCallableStatement(CallableStatement statement, LatencySimulator latencySimulator) {
        super(statement, latencySimulator);
        this.realCallableStatement = statement;
    }
}
