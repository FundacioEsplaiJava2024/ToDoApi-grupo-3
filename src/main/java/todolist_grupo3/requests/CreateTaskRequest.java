package todolist_grupo3.requests;

import lombok.Data;

@Data
public class CreateTaskRequest {
    private String name;

    public boolean isValid() {
        return name != null && !name.trim().isEmpty() && name.length() <= 20;
    }
}
