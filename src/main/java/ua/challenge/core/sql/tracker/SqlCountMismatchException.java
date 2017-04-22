package ua.challenge.core.sql.tracker;

/**
 * Created by d.bakal on 22.04.2017.
 */
public class SqlCountMismatchException extends RuntimeException {
    public SqlCountMismatchException(String statement, int expectedCount, int actualCount) {
        super("Expected " + statement + " query count : " + expectedCount + ", but was : " + actualCount);
    }
}
