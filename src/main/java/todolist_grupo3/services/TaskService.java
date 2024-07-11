package todolist_grupo3.services;

import java.util.List;

import todolist_grupo3.entities.Task;

public interface TaskService {
        List<Task> getAllTasks();
        Task getTaskById(Integer id);
        void changeState(Integer id);
}