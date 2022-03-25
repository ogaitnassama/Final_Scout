package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ScoutInfo;
import com.example.demo.service.ScoutService;


@RestController
public class Controller {
	
	
	private ScoutService service;

	public Controller(ScoutService service) {
		super();
		this.service = service;
	} 
	
	@PostMapping("/createReport")
	public ResponseEntity<String> createReport(@RequestBody ScoutInfo report) {
		
		// run the method in the Services class, passing in the object received via HTTP Request
		service.createReport(report); //add DB afterBooking
		
		// returns a response entity<data it contains>
		ResponseEntity<String> response = new ResponseEntity<>("Report added with ID : " + report.getId(), HttpStatus.CREATED);
		return response;
	}
	@GetMapping("/getReports")
	public ResponseEntity<List<ScoutInfo>>getReports(){
		
		List<ScoutInfo> response = service.getReports();
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/get/{Id}")
	public ResponseEntity<ScoutInfo> getById(@PathVariable ("Id")long Id){
		
		ScoutInfo result = service.getById(Id);
		
		ResponseEntity<ScoutInfo> response = new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		
		return response;
	}
	@GetMapping("/getRegion/{region}")
	public ResponseEntity<List<ScoutInfo>> getByRegion(@PathVariable("region")String region){
			
		List<ScoutInfo> response = service.getByRegion(region);
			
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String>deleteAll(){
		service.deleteAll();
		return new ResponseEntity<>("All Reports have been deleted", HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete/{Id}")
	public ResponseEntity<String> deleteById(@PathVariable("Id")long Id){
		service.remove(Id);
		String response = "Report of ID : " + Id + " deleted";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	@PutMapping("/update/{Id}")
	public ResponseEntity<String> updateById(@PathVariable("Id")long Id, @RequestBody ScoutInfo report){
	
		service.update(Id, report);
		
		String response = "Updated Report of ID : " + Id;
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	
	

}
