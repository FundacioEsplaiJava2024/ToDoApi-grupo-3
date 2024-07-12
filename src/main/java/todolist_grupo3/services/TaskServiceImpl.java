package todolist_grupo3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import todolist_grupo3.entities.State;
import todolist_grupo3.entities.Task;
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

}