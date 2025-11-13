package aston.first_stage_project;

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
