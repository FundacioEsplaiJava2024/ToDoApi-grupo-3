package todolist_grupo3.todolist_grupo3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import todolist_grupo3.todolist_grupo3.repo.TaskRepository;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

}