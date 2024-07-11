package todolist_grupo3.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

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
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Integer id){
        return taskRepository.findById(id).orElseThrow(()-> new HttpException(HttpStatus.NOT_FOUND,
                "Could not find any task with ID "+id));
    }

    @Override
    public void changeState(Integer id) {
        Optional<Task> taskToChange = taskRepository.findById(id);
        taskToChange.ifPresentOrElse(
            task -> {
                try {
                    State newState = changeStateTask(task);
                    task.setState(newState);
                    taskRepository.save(task);
                } catch (HttpException e) {
                     new HttpException(HttpStatus.CONFLICT, "The state couldn't be changed");
                }
            },
            () -> {
                new HttpException(HttpStatus.NOT_FOUND, "The task with id " +id+" wasn't found");
            }
        );
        
    }
    /*
     * method for changeState(). In case when the state of the task is 'INCOMPLETE',
     * returns State.COMPLETE. In other case means that the state is 'COMPLETE',
     * so it returns State.INCOMPLETE
     * it's private because it's for internal usage and we don't want to have access from another classes,
     *  this way it also doesn't appear in TaskService. 
     */
    private State changeStateTask(Task task){
        if(task.getState().equals(State.INCOMPLETE)){
            return State.COMPLETE;
        }else{
            return State.INCOMPLETE;
        }
    }

}