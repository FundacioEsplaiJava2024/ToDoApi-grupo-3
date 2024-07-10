package todolist_grupo3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import todolist_grupo3.entities.Task;
import todolist_grupo3.repo.TaskRepository;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void deleteTask (Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void editTask(Integer id, String name) {
        Task existingTask = taskRepository.findById(id).orElseThrow();
        existingTask.setName(name);
        taskRepository.save(existingTask);
    }

}