package todolist_grupo3.todolist_grupo3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import todolist_grupo3.todolist_grupo3.services.TaskService;


@RestController
@RequestMapping("/todolist")
@AllArgsConstructor
public class TaskController {
    @Autowired
    private TaskService taskService;

    @DeleteMapping ("/{id}")
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
    }
}