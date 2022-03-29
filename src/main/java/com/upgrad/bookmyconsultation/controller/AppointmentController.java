package com.upgrad.bookmyconsultation.controller;

import com.upgrad.bookmyconsultation.entity.Appointment;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.exception.SlotUnavailableException;
import com.upgrad.bookmyconsultation.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	//create a method post method named bookAppointment with return type ReponseEntity
		//method has paramter of type Appointment, use RequestBody Annotation for mapping
	@PostMapping(value = "/")
	public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) throws InvalidInputException {

		//save the appointment details to the database and save the response from the method used
		String response = appointmentService.appointment(appointment);
		//return http response using ResponseEntity
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	//create a get method named getAppointment with return type as ResponseEntity
		//method has appointmentId of type String. Use PathVariable annotation to identity appointment using the parameter defined
	@GetMapping(value = "/{appointmentId}")
	public ResponseEntity<Appointment> getAppointment(@PathVariable(name = "appointmentId") String appointmentId) {
			//get the appointment details using the appointmentId
		Appointment response = appointmentService.getAppointment(appointmentId);
			//save the response
			//return the response as an http response
			return ResponseEntity.ok(response);
		}

}