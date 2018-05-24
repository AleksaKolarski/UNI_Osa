package projekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.dto.UserDTO;
import projekat.entity.User;
import projekat.service.UserServiceInterface;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserServiceInterface userService;
	
	// GET ALL
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getUsers(){
		List<User> users = userService.findAll();
		List<UserDTO> usersDTO = new ArrayList<>();
		for(User user: users) {
			usersDTO.add(new UserDTO(user));
		}
		return new ResponseEntity<List<UserDTO>>(usersDTO, HttpStatus.OK);
	}
	
	// GET BY USERNAME
	@GetMapping(value = "/{username}")
	public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username){
		User user = userService.findByUsername(username);
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}
	
	// GET BY USERNAME AND PASSWORD
	@GetMapping(value = "/{username}/{password}")
	public ResponseEntity<UserDTO> getUserByUsernameAndPassword(@PathVariable("username") String username, @PathVariable("password") String password){
		User user = userService.findByUsernameAndPassword(username, password);
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}
	
	// EDIT
	@PutMapping(consumes = "application/json")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
		User user = userService.findByUsername(userDTO.getUsername());
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}
		
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		user.setPhoto(userDTO.getPhoto());
		
		userService.save(user);
		
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}
	
	// CREATE
	@PostMapping(consumes = "application/json")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
		User user = userService.findByUsername(userDTO.getUsername());
		
		// Vec postoji korisnik sa tim username-om
		if(user != null) {
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}
		
		// Prekratak username ili password
		if(userDTO.getUsername().length() < 3 || userDTO.getPassword().length() < 3) {
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}
		
		
		user = new User();
		
		user.setName(userDTO.getName());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		
		userService.save(user);
		
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.CREATED);
	}
	
	// DELETE
	@DeleteMapping(value = "/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
		User user = userService.findByUsername(username);
		if(user == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		userService.remove(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
