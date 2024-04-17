package com.saparov.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "limits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Limit {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "client_account_id")
	private Long clientAccountId;
	
	@Column(name = "month")
	private int month;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "limit_amount")
	private BigDecimal limitAmount;
	
	@Column(name = "limit_currency_short_name")
	private String limitCurrencyShortName;
	
	@Column(name = "limit_datetime")
	private LocalDate limitDatetime;
	
}