package com.upgrad.bookmyconsultation.controller;

import com.upgrad.bookmyconsultation.entity.User;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.service.AppointmentService;
import com.upgrad.bookmyconsultation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserAdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private AppointmentService appointmentService;


	@GetMapping(path = "/{id}")
	public ResponseEntity<User> getUser(@RequestHeader("authorization") String accessToken,
	                                    @PathVariable("id") final String userUuid) {
		final User User = userService.getUser(userUuid);
		return ResponseEntity.ok(User);
	}
	
	//create a post method named createUser with return type as ResponseEntity
		//define the method parameter user of type User. Set it final. Use @RequestBody for mapping.
		//declare InvalidInputException using throws keyword
	@PostMapping(value = "/register")
	public ResponseEntity createUser(@RequestBody final User user) throws InvalidInputException {

		//register the user
		User newUser = userService.register(user);

		//return http response with status set to OK
		return ResponseEntity.ok(newUser);

	}

	@GetMapping("/{userId}/appointments")
	public ResponseEntity getAppointmentForUser(@PathVariable("userId") String userId) {
		return ResponseEntity.ok(appointmentService.getAppointmentsForUser(userId));
	}


}