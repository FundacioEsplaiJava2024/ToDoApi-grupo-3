package todolist_grupo3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import todolist_grupo3.entities.State;
import todolist_grupo3.entities.Task;
import todolist_grupo3.exception.HttpException;
import todolist_grupo3.repo.TaskRepository;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(String name) {
        Task newTask= new Task();
        newTask.setName(name);
        newTask.setState(State.INCOMPLETE);
        return taskRepository.save(newTask);
    }
    
    @Override
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Integer id){
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTask (Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void editTask(Integer id, String name) {
        taskRepository.findById(id).orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND,
            "could not find any tank with this id" + id));
        if (name == null || name.trim().isEmpty() || name.length() > 20) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "name must be between 1 and 20 characters");
        }
        Task existingTask = taskRepository.findById(id).get();
        existingTask.setName(name);
        taskRepository.save(existingTask);
    }
}