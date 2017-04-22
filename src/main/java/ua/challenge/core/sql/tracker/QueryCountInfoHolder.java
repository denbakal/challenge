package ua.challenge.core.sql.tracker;

/**
 * Created by d.bakal on 22.04.2017.
 */
public class QueryCountInfoHolder {
    private static ThreadLocal<QueryCountInfo> queryCountInfo = ThreadLocal.withInitial(QueryCountInfo::new);

    public static QueryCountInfo getQueryInfo() {
        return queryCountInfo.get();
    }

    public static void clear() {
        queryCountInfo.get().clear();
    }
}
