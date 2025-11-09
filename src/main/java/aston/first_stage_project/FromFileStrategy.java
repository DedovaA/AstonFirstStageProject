package aston.first_stage_project;

import java.util.List;

public class FromFileStrategy implements DataSource{
    @Override
    public List<Bus> getBusList() {
        return List.of();
    }
}
