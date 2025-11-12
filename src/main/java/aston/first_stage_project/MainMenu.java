package aston.first_stage_project;

public class MainMenu {
    private DataSource[] strategyArr;

    public MainMenu(DataSource[] strategyArr) {
        this.strategyArr = strategyArr;
    }

    String getMenu() {
        StringBuilder message = new StringBuilder("Выберите источник ввода данных для сортировки:\n");
        for (int i = 0; i < this.strategyArr.length; i++) {
            message.append((i + 1)).append(" - ").append(this.strategyArr[i].toString()).append("\n");
        }
        message.append("Для выхода из программы введите Q.");
        return message.toString();
    }
}
