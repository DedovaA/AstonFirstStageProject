package aston.first_stage_project;

class MenuEntry {
    private String key;
    private String desc;
    private DataSource value;

    public MenuEntry(String key, String desc, DataSource value) {
        this.key = key;
        this.desc = desc;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public DataSource getValue() {
        return value;
    }
}
