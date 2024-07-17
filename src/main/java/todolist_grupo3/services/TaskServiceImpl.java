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
    public Task editTask(Integer id, String name) {
        Task existingTask = taskRepository.findById(id).get();
        existingTask.setName(name);
        return taskRepository.save(existingTask);
    }

    @Override
    public void changeState(Integer id, State state) {
        Task task = getTaskById(id);
        task.setState(state);
        taskRepository.save(task); 
    }

    /*
     * method for changeState(). In case when the state of the task is 'INCOMPLETE',
     * returns State.COMPLETE. In other case means that the state is 'COMPLETE',
     * so it returns State.INCOMPLETE
     * it's private because it's for internal usage and we don't want to have access from another classes,
     *  this way it also doesn't appear in TaskService. 
     */
    @Override
    public State changeStateTask(Task task){
        if(task.getState().equals(State.INCOMPLETE)){
            return State.COMPLETE;
        }else{
            return State.INCOMPLETE;
        }
    }

}