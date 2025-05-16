package eaut.it.java_tech_course.TodoManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @GetMapping("/403")
    public String handleAccessDenied() {
        logger.info("Access denied for user");
        return "error/403";
    }

    @GetMapping("/404")
    public String handleNotFound() {
        logger.info("Page not found");
        return "error/404";
    }
    
    @GetMapping("/500")
    public String handleInternalServerError() {
        logger.error("Internal server error occurred");
        return "error/500";
    }
}