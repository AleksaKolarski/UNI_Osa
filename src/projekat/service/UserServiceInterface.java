package projekat.service;

import java.util.List;

import projekat.entity.User;

public interface UserServiceInterface {
	
	List<User> findAll();
	
	User findByUsername(String username);
	
	User findByUsernameAndPassword(String username, String password);
	
	User findById(Integer userId);
	
	User save(User user);
	
	void remove(User user);
}
