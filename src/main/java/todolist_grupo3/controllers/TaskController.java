package todolist_grupo3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import todolist_grupo3.entities.State;
import todolist_grupo3.entities.Task;
import todolist_grupo3.exception.HttpException;
import todolist_grupo3.requests.CreateTaskRequest;
import todolist_grupo3.requests.EditNameRequest;
import todolist_grupo3.services.TaskService;


@RestController
@RequestMapping("/todolist")
@AllArgsConstructor
public class TaskController {
    @Autowired
    private TaskService taskService;
    
    @PostMapping("/task")
    @CrossOrigin("*")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        try {
            String name= createTaskRequest.getName();
            if (name == null || name.trim().isEmpty() || name.length() > 20) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Error: name must be between 1 and 20 characters");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(createTaskRequest.getName()));
        } catch (HttpException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }

    @GetMapping("/tasks")
    @CrossOrigin("*")
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks());
      
    }

    @GetMapping("/task/{id}")
    @CrossOrigin("*") 
    public ResponseEntity<?> getTaskById(@PathVariable Integer id) {
        try {
            Task task = taskService.getTaskById(id);
            if (task == null) {
                throw new HttpException(HttpStatus.NOT_FOUND,"Error: Task not found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (HttpException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }

    @DeleteMapping ("/task/{id}")
    @CrossOrigin("*")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        try {
            Task task = taskService.getTaskById(id);
            if (task == null) {
                throw new HttpException(HttpStatus.NOT_FOUND,"Error: Task not found");
            }
            
            taskService.deleteTask(id);
            return ResponseEntity.status(HttpStatus.OK).body("Task deleted");
        } catch (HttpException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }

    @PutMapping("/task/{id}")
    @CrossOrigin("*")
    public ResponseEntity<?> editTask(@PathVariable Integer id, @RequestBody EditNameRequest editNameRequest){
        try {
            Task task = taskService.getTaskById(id);
            String name= editNameRequest.getName();
            if (task == null) {
                throw new HttpException(HttpStatus.NOT_FOUND,"Error: Task not found");
            }
            else if (name == null || name.trim().isEmpty() || name.length() > 20) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Error: name must be between 1 and 20 characters");
            }
            return ResponseEntity.status(HttpStatus.OK).body(taskService.editTask(id, editNameRequest.getName()));
        } catch (HttpException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }

    @PatchMapping("/task/{id}")
    @CrossOrigin("*")
    public ResponseEntity<?> changeTaskStateById(@PathVariable Integer id) {
        try{
            Task task = taskService.getTaskById(id);
            if (task == null) {
                throw new HttpException(HttpStatus.NOT_FOUND,"Error: Task not found");
            }

            State newState = taskService.changeStateTask(task);
            if (newState.equals(task.getState())) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Error: Task state not changed");
            }
            taskService.changeState(id, newState);
            return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
        }catch(HttpException ex){
            return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
        }
    }
}   