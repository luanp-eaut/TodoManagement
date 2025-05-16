package eaut.it.java_tech_course.TodoManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eaut.it.java_tech_course.TodoManagement.entity.Task;
import eaut.it.java_tech_course.TodoManagement.entity.User;
import eaut.it.java_tech_course.TodoManagement.repository.TaskRepository;
import eaut.it.java_tech_course.TodoManagement.repository.UserRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public Task createTask(Task task, User user) {
		if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
			throw new IllegalArgumentException("Task title cannot be empty");
		}
		task.setUser(user);
		return taskRepository.save(task);
	}

	public List<Task> findTasksByUser(User user) {
		return taskRepository.findByUser(user);
	}

	@Transactional
	public Task updateTask(Long id, Task task, User user) {
		Task existingTask = taskRepository.findById(id).orElseThrow();
		if (!existingTask.getUser().getId().equals(user.getId())) {
			throw new SecurityException("Unauthorized");
		}
		existingTask.setTitle(task.getTitle());
		existingTask.setDescription(task.getDescription());
		existingTask.setCompleted(task.isCompleted());
		return taskRepository.save(existingTask);
	}

	@Transactional
	public void deleteTask(Long id, User user) {
		Task task = taskRepository.findById(id).orElseThrow();
		if (!task.getUser().getId().equals(user.getId())) {
			throw new SecurityException("Unauthorized");
		}
		taskRepository.deleteById(id);
	}

	public Optional<Task> findTaskById(Long id) {
		return taskRepository.findById(id);
	}

	@Transactional
	public void saveTask(Task task, String username) {
		if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
			throw new IllegalArgumentException("Task title cannot be empty");
		}
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new IllegalArgumentException("User not found");
		}
		task.setUser(user);
		taskRepository.save(task);
	}
}
