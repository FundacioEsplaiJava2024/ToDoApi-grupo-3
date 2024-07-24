package todolist_grupo3.requests;

import lombok.Data;

@Data
public class CreateTaskRequest {
    private String name;
    private String description;
}
