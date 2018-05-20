package projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.dto.UserDTO;
import projekat.entity.User;
import projekat.service.UserServiceInterface;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private UserServiceInterface userService;
	
	// LOGIN
	@GetMapping(value = "/{username}/{password}")
	public ResponseEntity<UserDTO> login(@PathVariable("username") String username, @PathVariable("password") String password){
		User user = userService.findByUsernameAndPassword(username, password);
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		user.setPhoto(null);
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}
}
