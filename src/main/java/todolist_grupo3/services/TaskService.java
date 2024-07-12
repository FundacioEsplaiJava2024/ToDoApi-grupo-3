package todolist_grupo3.services;

import java.util.List;

import todolist_grupo3.entities.Task;

public interface TaskService {
        public Task create (Task task);
        List<Task> getAllTasks();
        Task getTaskById(Integer id);
}