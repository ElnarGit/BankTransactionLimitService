package com.saparov.app.service;

import com.saparov.app.exception.LimitNotFoundException;
import com.saparov.app.model.Limit;
import com.saparov.app.repository.LimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LimitService {
	private final LimitRepository limitRepository;
	
	public Limit findLimitById(Long id){
		return limitRepository.findById(id)
				.orElseThrow(() -> new LimitNotFoundException("Limit not found with id: " + id));
	}
	
	public List<Limit> findAllLimits(){
		return limitRepository.findAll();
	}
	
	public Limit findLimitByClientAccountId(Long clientId){
		return limitRepository.findByClientAccountId(clientId)
				.orElseThrow(() -> new LimitNotFoundException("Limit not found with id: " + clientId));
	}
	
	@Transactional
	public Limit createLimit(Limit limit){
		return limitRepository.save(limit);
	}
	
	@Transactional
	public Limit updateLimit(Long id, Limit updatedLimit){
		Limit limit = limitRepository.findById(id)
				.orElseThrow(() -> new LimitNotFoundException("Limit not found with id: " + id));
		
		limit.setClientAccountId(updatedLimit.getClientAccountId());
		limit.setMonth(updatedLimit.getMonth());
		limit.setYear(updatedLimit.getYear());
		limit.setLimitAmount(updatedLimit.getLimitAmount());
		limit.setLimitCurrencyShortName(updatedLimit.getLimitCurrencyShortName());
		limit.setLimitDatetime(updatedLimit.getLimitDatetime());
		
		return limitRepository.save(limit);
	}
	
	public void deleteLimitById(Long id){
		limitRepository.findById(id)
				.orElseThrow(() -> new LimitNotFoundException("Limit not found with id: " + id));
		
		limitRepository.deleteById(id);
	}
}
