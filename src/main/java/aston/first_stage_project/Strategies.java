package aston.first_stage_project;

enum Strategies {
    FROM_FILE("из файла"),
    MANUALLY("вручную"),
    RANDOM("рандомно");

    private final String title;

    Strategies(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Strategy {" + title + "}";
    }
}
