package todolist_grupo3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import todolist_grupo3.services.TaskService;


@RestController
@RequestMapping("/todolist")
@AllArgsConstructor
public class TaskController {
    @Autowired
    private TaskService taskService;

    @DeleteMapping ("/task/{id}")
    @CrossOrigin("*")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted");
    }

    @PutMapping("/task/{id}")
    @CrossOrigin("*")
    public ResponseEntity<?> editTask(@PathVariable Integer id, @RequestBody String name) {
        taskService.editTask(id, name);
        return ResponseEntity.status(HttpStatus.OK).body("Task edited");
    }
}