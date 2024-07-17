package todolist_grupo3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
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
            return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(createTaskRequest.getName()));
        } catch (HttpException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
    @GetMapping("/tasks")
    @CrossOrigin("*")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks());
      
    }

    @GetMapping("/task/{id}")
    @CrossOrigin("*") 
    public ResponseEntity<?> getTaskById(@PathVariable Integer id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @DeleteMapping ("/task/{id}")
    @CrossOrigin("*")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body("tarea " + id +" eliminada");
    }

    @PutMapping("/task/{id}")
    @CrossOrigin("*")
    public ResponseEntity<?> editTask(@PathVariable Integer id, @RequestBody EditNameRequest editNameRequest) {
        try {
            Task task=taskService.getTaskById(id);
            taskService.editTask(id, editNameRequest.getName());
            return ResponseEntity.status(HttpStatus.OK).body(task);
        } catch (HttpException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
}   