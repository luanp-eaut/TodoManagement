package eaut.it.java_tech_course.TodoManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eaut.it.java_tech_course.TodoManagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
	boolean existsByUsername(String username);
}
