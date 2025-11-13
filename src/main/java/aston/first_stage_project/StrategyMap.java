package aston.first_stage_project;

import java.util.HashMap;
import java.util.Map;

class StrategyMap {
    private final Map<String, MapEntry> strategyMap = new HashMap<>();
    private String key;
    private MapEntry mapEntry;

    public StrategyMap() {
    }

    public void addStrategy(String key, MapEntry mapEntry) {
        strategyMap.put(key, mapEntry);
    }

    public Map<String, MapEntry> getStrategyMap() {
        return strategyMap;
    }
}

class MapEntry {
    private final DataSource strategy;
    private final String description;

    public MapEntry(DataSource strategy, String description) {
        this.strategy = strategy;
        this.description = description;
    }

    public DataSource getStrategy() {
        return strategy;
    }

    public String getDescription() {
        return description;
    }
}