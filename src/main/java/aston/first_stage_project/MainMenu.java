package aston.first_stage_project;

import java.util.List;

class MainMenu {
    List<MenuEntry> menuEntryList;

    public MainMenu(List<MenuEntry> listStrategy) {
        this.menuEntryList = listStrategy;
    }

    String getMenu() {
        StringBuilder message = new StringBuilder("Выберите источник ввода данных для сортировки:\n");
        for(MenuEntry entry: menuEntryList) {
            message.append(entry.getKey()).append(" - ").append(entry.getDesc()).append("\n");
        }
        message.append("Для выхода из программы введите Q.");
        return message.toString();
    }
}
