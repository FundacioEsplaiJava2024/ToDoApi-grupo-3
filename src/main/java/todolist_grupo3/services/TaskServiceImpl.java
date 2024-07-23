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
    public Task createTask(String name,String description) {
        Task newTask= new Task();
        newTask.setName(name);
        newTask.setDescription(description);
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
    public Task editTask(Integer id,  String name, String description) {
        Task existingTask = taskRepository.findById(id).get();
        if(name==null || name.trim().isEmpty()){
            existingTask.setDescription(description);
            //existingTask.getName();
        }
        else if(description==null || description.trim().isEmpty()){  
            existingTask.setName(name);
        }
        else {
            existingTask.setName(name);
            existingTask.setDescription(description);
        }

        return taskRepository.save(existingTask);
    }

    /*@Override
    public Task editDescription(Integer id, String description){
        Task existingTask = taskRepository.findById(id).get();
        existingTask.setDescription(description);
        return taskRepository.save(existingTask);
    }*/

    @Override
    public void changeState(Integer id) {
        Task task = taskRepository.findById(id).get();
        if(task.getState().equals(State.INCOMPLETE)){
            task.setState(State.COMPLETE);
        }else{
            task.setState(State.INCOMPLETE);
        }
        taskRepository.save(task);
    }
}