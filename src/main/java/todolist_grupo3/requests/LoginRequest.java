package todolist_grupo3.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotEmpty(message = "Name is mandatory")
    @NotNull(message = "Name is mandatory")
    @Size(min = 8, message = "Username should be 8 characters long minimum")
    private String username;


    @NotEmpty(message = "Password is mandatory")
    @NotNull(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;
}
