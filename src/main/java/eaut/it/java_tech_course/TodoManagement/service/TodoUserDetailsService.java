package eaut.it.java_tech_course.TodoManagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import eaut.it.java_tech_course.TodoManagement.entity.User;
import eaut.it.java_tech_course.TodoManagement.model.TodoUserDetails;
import eaut.it.java_tech_course.TodoManagement.repository.UserRepository;

@Service
public class TodoUserDetailsService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(TodoUserDetailsService.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new TodoUserDetails(user);
//        User user = userRepository.findByUsername(username);
//        
//       
//        if (user == null) {
//        	 logger.error("--------------User not found: {}", username);
//        	 throw new UsernameNotFoundException("User not found");
//        }
//        return new org.springframework.security.core.userdetails.User(
//            user.getUsername(),
//            user.getPassword(),
//            Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
//        );
    }
}
