package com.saparov.app.repository;

import com.saparov.app.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
	Optional<ExchangeRate> findByCurrencyPairAndDate(String currencyPair, LocalDate date);
	
	@Query("SELECT er FROM ExchangeRate er WHERE er.currencyPair = :currencyPair ORDER BY er.date DESC")
	ExchangeRate findLastExchangeRateByCurrencyPair(@Param("currencyPair") String currencyPair);
}
