package aston.first_stage_project;

import java.util.Map;

class MainMenu {
    private final StrategyMap strategyMap;

    public MainMenu(StrategyMap strategyMap) {
        this.strategyMap = strategyMap;
    }

    String getMenu() {
        StringBuilder message = new StringBuilder("Выберите источник ввода данных для сортировки:\n");
        for(Map.Entry<String, MapEntry> entry:strategyMap.getStrategyMap().entrySet()) {
            message.append(entry.getKey()).append(" - ").append(entry.getValue().getDescription()).append("\n");
        }
        message.append("Для выхода из программы введите Q.");
        return message.toString();
    }
}
