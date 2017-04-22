package ua.challenge.core.sql.tracker;

import lombok.Getter;

/**
 * Created by d.bakal on 22.04.2017.
 */
@Getter
public class QueryCountInfo {
    private int selectCount;
    private int insertCount;
    private int updateCount;
    private int deleteCount;
    private int callCount;

    public void incrementSelectCount() {
        selectCount++;
    }

    public void incrementInsertCount() {
        insertCount++;
    }

    public void incrementUpdateCount() {
        updateCount++;
    }

    public void incrementDeleteCount() {
        deleteCount++;
    }

    public void incrementCallCount() {
        callCount++;
    }

    public void clear() {
        selectCount = 0;
        insertCount = 0;
        updateCount = 0;
        deleteCount = 0;
        callCount = 0;
    }

    public int countAll() {
        return selectCount + insertCount + updateCount + deleteCount + callCount;
    }
}
