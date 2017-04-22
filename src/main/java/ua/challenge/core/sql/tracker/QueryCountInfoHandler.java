package ua.challenge.core.sql.tracker;

/**
 * Created by d.bakal on 22.04.2017.
 */
public class QueryCountInfoHandler implements QueryHandler {
    @Override
    public void handleSql(String sql) {
        QueryType queryType = getQueryType(sql);
        QueryCountInfo queryCountInfo = QueryCountInfoHolder.getQueryInfo();
        switch (queryType) {
            case SELECT:
                queryCountInfo.incrementSelectCount();
                break;
            case INSERT:
                queryCountInfo.incrementInsertCount();
                break;
            case UPDATE:
                queryCountInfo.incrementUpdateCount();
                break;
            case DELETE:
                queryCountInfo.incrementDeleteCount();
                break;
            case CALL:
                queryCountInfo.incrementCallCount();
                break;
        }
    }

    private QueryType getQueryType(String query) {
        final String trimmedQuery = removeRedundantSymbols(query);
        final char firstChar = trimmedQuery.charAt(0);

        final QueryType type;
        switch (firstChar) {
            case 'w': // query can be started 'with'
            case 's':
                type = QueryType.SELECT;
                break;
            case 'i':
                type = QueryType.INSERT;
                break;
            case 'u':
                type = QueryType.UPDATE;
                break;
            case 'd':
                type = QueryType.DELETE;
                break;
            case 'c':
            case '?':
                type = QueryType.CALL;
                break;
            case 'm':
                type = QueryType.MERGE;
                break;
            default:
                throw new AssertionError("Wrong QueryType");
        }
        return type;
    }

    private String removeRedundantSymbols(String query) {
        return query.replaceAll("--.*\n", "").replaceAll("\n", "").replaceAll("/\\*.*\\*/", "").trim();
    }
}
