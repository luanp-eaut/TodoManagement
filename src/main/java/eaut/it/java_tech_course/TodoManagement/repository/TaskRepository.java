package eaut.it.java_tech_course.TodoManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eaut.it.java_tech_course.TodoManagement.entity.Task;
import eaut.it.java_tech_course.TodoManagement.entity.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
