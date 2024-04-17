package com.saparov.app.controller;

import com.saparov.app.model.Limit;
import com.saparov.app.service.LimitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/limits")
@RequiredArgsConstructor
public class LimitController {
	private final LimitService limitService;
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Limit getLimitById(@PathVariable("id") Long id){
		return limitService.findLimitById(id);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Limit> getAllLimits(){
		return limitService.findAllLimits();
	}
	
	@GetMapping("/client-account-id/{clientAccountId}")
	@ResponseStatus(HttpStatus.OK)
	public Limit getLimitByClientAccountId(@PathVariable("clientAccountId") Long clientAccountId){
		return limitService.findLimitByClientAccountId(clientAccountId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Limit createLimit(@Valid @RequestBody Limit limit){
		return limitService.createLimit(limit);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Limit updateLimit(@PathVariable("id") Long id, @Valid @RequestBody Limit updatedLimit){
		return limitService.updateLimit(id, updatedLimit);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteLimitById(@PathVariable("id") Long id){
		limitService.deleteLimitById(id);
		
		return "Limit deleted";
	}
}
