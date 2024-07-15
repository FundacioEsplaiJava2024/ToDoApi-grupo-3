package todolist_grupo3.services;

import java.util.List;

import todolist_grupo3.entities.Task;

public interface TaskService {

        Task createTask (String name);
        List<Task> getAllTasks();
        Task getTaskById(Integer id);
        void deleteTask (Integer id);
        void editTask(Integer id, String name);
}