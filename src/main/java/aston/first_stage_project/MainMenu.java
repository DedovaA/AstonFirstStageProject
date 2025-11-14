package aston.first_stage_project;

import java.util.List;

class MainMenu {
   private final List<MenuEntry> entryList;
   private String text;
   private final String quitKey;

    public MainMenu(List<MenuEntry> entryList, String quitKey) {
        this.entryList = entryList;
        this.quitKey = quitKey;
        buildText();
    }

    public String getText() {
        return text;
    }

    public boolean isValidKey(String text) {
        return entryList.stream().anyMatch(
                entry -> entry.getKey().equals(text));
    }

    public boolean isQuitKey(String text) {
        return quitKey.equalsIgnoreCase(text);
    }

    private void buildText() {
        StringBuilder textBuilder =
                new StringBuilder("Выберите источник ввода данных для сортировки:\n");
        entryList.forEach(entry -> textBuilder
                .append(entry.getKey())
                .append(" - ")
                .append(entry.getDesc())
                .append("\n")
        );
        textBuilder.append("Для выхода из программы введите \"")
                .append(quitKey)
                .append("\".");
        text = textBuilder.toString();
    }
}
