package todolist_grupo3.requests;

import java.util.Optional;

import lombok.Getter;

@Getter
public class EditTaskRequest {

    private Optional<String> name;

    private Optional<String> description;

    public EditTaskRequest(Optional<String> name, Optional<String> description) {
        this.name = name;
        this.description = description;
    }

}
