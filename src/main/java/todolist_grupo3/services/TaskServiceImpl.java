package todolist_grupo3.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import todolist_grupo3.entities.State;
import todolist_grupo3.entities.Task;
import todolist_grupo3.entities.User;
import todolist_grupo3.repo.TaskRepository;
import todolist_grupo3.repo.UserRepository;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public Task createTask(String name, String description) {
        Task newTask = new Task();
        newTask.setName(name);
        newTask.setDescription(description);
        newTask.setState(State.INCOMPLETE);
        newTask.setUser(getLoggedUserId());
        return taskRepository.save(newTask);
    }

    @Override
    public List<Task> getAllTasks() {
        User loggedUser = getLoggedUserId();
        List<Task> loggedUsersTasks = taskRepository.findAll().stream()
        .filter(task -> task.getUser().equals(loggedUser)).collect(Collectors.toList());
        return loggedUsersTasks;
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task editTask(Integer id, String name, String description) {
        Task existingTask = taskRepository.findById(id).get();
        if (name == null || name.trim().isEmpty()) {
            name = existingTask.getName();
        }
        existingTask.setName(name);
        existingTask.setDescription(description);

        return taskRepository.save(existingTask);
    }

    @Override
    public void changeState(Integer id) {
        Task task = taskRepository.findById(id).get();
        if (task.getState().equals(State.INCOMPLETE)) {
            task.setState(State.COMPLETE);
        } else {
            task.setState(State.INCOMPLETE);
        }
        taskRepository.save(task);
    }

    private User getLoggedUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Optional<User> optionalUser = userRepository.findByUsername(userName);
        return optionalUser.get();
    }
}
