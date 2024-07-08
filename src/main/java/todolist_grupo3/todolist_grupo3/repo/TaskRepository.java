package todolist_grupo3.todolist_grupo3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import todolist_grupo3.todolist_grupo3.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

}
