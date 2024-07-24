package todolist_grupo3.services;

import java.util.List;

import todolist_grupo3.entities.Task;

public interface TaskService {
        Task createTask (String name, String description);
        List<Task> getAllTasks();
        Task getTaskById(Integer id);
        void deleteTask (Integer id);
        Task editTask(Integer id, String name, String description);
        void changeState(Integer id);
}