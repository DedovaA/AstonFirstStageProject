package aston.first_stage_project;

import java.util.HashMap;
import java.util.Map;

public class StrategyMap {
    private DataSource[] strategyArr;

    public StrategyMap(DataSource[] strategyArr) {
        this.strategyArr = strategyArr;
    }

    Map<String, DataSource> getMap() {
        Map<String, DataSource> map = new HashMap<>();
        for (int i = 0; i < this.strategyArr.length; i++) {
            map.put(String.valueOf(i + 1), this.strategyArr[i]);
        }
        return map;
    }
}
