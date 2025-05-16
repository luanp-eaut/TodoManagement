package eaut.it.java_tech_course.TodoManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eaut.it.java_tech_course.TodoManagement.entity.Task;
import eaut.it.java_tech_course.TodoManagement.model.TodoUserDetails;
import eaut.it.java_tech_course.TodoManagement.service.TaskService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    
    @GetMapping
    public String listTasks(@AuthenticationPrincipal TodoUserDetails user, Model model) {
        model.addAttribute("tasks", taskService.findTasksByUser(user.getUser()));
        return "task/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        logger.info("Showing task create form");
        model.addAttribute("task", new Task());
        model.addAttribute("isEdit", false);
        return "task/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        logger.info("Showing task edit form for id={}", id);
        Task task = taskService.findTaskById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        model.addAttribute("task", task);
        model.addAttribute("isEdit", true);
        return "task/form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult, 
                          Model model, Authentication authentication) {
//        logger.debug("BindingResult has errors: {}", bindingResult.hasErrors());
//        logger.debug("BindingResult errors: {}", bindingResult.getAllErrors());
        if (bindingResult.hasErrors()) {
            logger.error("Validation errors: {}", bindingResult.getAllErrors());
            model.addAttribute("task", task);
            model.addAttribute("isEdit", task.getId() != null);
            return "task/form";
        }
        try {
            String username = authentication.getName();
            taskService.saveTask(task, username);
        } catch (IllegalArgumentException e) {
            logger.error("Task save error: {}", e.getMessage());
            bindingResult.rejectValue("title", "error.task", e.getMessage());
            model.addAttribute("task", task);
            model.addAttribute("isEdit", task.getId() != null);
            return "tasks/form";
        }
        logger.info("Task saved successfully, redirecting to task list");
        return "redirect:/tasks";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, @AuthenticationPrincipal TodoUserDetails user) {
        taskService.deleteTask(id, user.getUser());
        return "redirect:/tasks";
    }
}