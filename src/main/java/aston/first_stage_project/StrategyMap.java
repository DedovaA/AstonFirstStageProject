package aston.first_stage_project;

import java.util.HashMap;
import java.util.Map;

public class StrategyMap {
    private final Map<String, DataSource> map = new HashMap<>();

    public StrategyMap(DataSource[] strategyArr) {
        for (int i = 0; i < strategyArr.length; i++) {
            map.put(String.valueOf(i + 1), strategyArr[i]);
        }
    }

    public Map<String, DataSource> getMap() {
        return map;
    }
}
